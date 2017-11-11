package com.jiangKlijna.web.app

import javax.servlet.ServletContext

import org.springframework.context.ApplicationContext
import org.springframework.web.context.ContextLoader
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import java.nio.charset.Charset

open class ContextWrapper {
    private var context: ApplicationContext? = null

    val webApplicationContext: WebApplicationContext
        get() = ContextLoader.getCurrentWebApplicationContext()

    val servletContext: ServletContext
        get() = webApplicationContext.servletContext

    val applicationContext: ApplicationContext
        get() = synchronized(ContextWrapper::class.java) {
            if (context == null) context = WebApplicationContextUtils.getWebApplicationContext(servletContext)
            return context!!
        }

    fun <T> getBean(name: String): T {
        return applicationContext.getBean(name) as T
    }

    fun encrypt(s: String) = DesUtils.du.encrypt(s)

    fun decrypt(s: String) = DesUtils.du.decrypt(s)

    fun reString(s: String): String = s.toByteArray(Charset.forName("iso8859-1")).let { String(it) }
}
