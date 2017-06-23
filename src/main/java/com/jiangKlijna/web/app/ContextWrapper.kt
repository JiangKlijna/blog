package com.jiangKlijna.web.app

import javax.servlet.ServletContext

import org.springframework.context.ApplicationContext
import org.springframework.web.context.ContextLoader
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

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

    fun getDesUtils(): DesUtils {
        return getBean("DesUtils")
    }
}
