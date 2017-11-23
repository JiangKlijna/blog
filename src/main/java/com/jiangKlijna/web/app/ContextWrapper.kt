package com.jiangKlijna.web.app

import com.jiangKlijna.web.util.DesUtils
import org.springframework.context.ApplicationContext
import org.springframework.web.context.ContextLoader
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import java.util.concurrent.Executors
import javax.servlet.ServletContext

open class ContextWrapper {

    val webApplicationContext: WebApplicationContext
        get() = ContextLoader.getCurrentWebApplicationContext()

    val servletContext: ServletContext
        get() = webApplicationContext.servletContext

    val applicationContext: ApplicationContext by lazy {
        WebApplicationContextUtils.getWebApplicationContext(servletContext)
    }

    // 上下文无关
    companion object {
        // 线程池,主要作用是生成message对象,并发布
        private val pool = Executors.newCachedThreadPool()
        val execute = fun(command: Runnable) = pool.execute(command)
        // 加密解密字符串
        val encrypt = fun(s: String) = DesUtils.du.encrypt(s)
        val decrypt = fun(s: String) = DesUtils.du.decrypt(s)
        //    fun reString(s: String): String = s.toByteArray(Charset.forName("iso8859-1")).let { String(it) }
    }
}
