package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.bean.Result
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

open class BaseControl : ContextWrapper() {

    protected var request: HttpServletRequest? = null
    protected var response: HttpServletResponse? = null
    protected var session: HttpSession? = null

    //获得session里面存储的username
    var sess_username: String? = null
        get() = session!!.getAttribute(TOKEN).let {
            if (it != null) decrypt(it as String) else null
        }

    @ModelAttribute
    fun setReqAndRes(request: HttpServletRequest, response: HttpServletResponse) {
        this.request = request
        this.response = response
        this.session = request.session
    }

    protected fun response(contentType: String, content: String) {
        response!!.contentType = contentType
        response!!.writer.apply {
            print(content)
            flush()
            close()
        }
    }

    companion object {
        const val TOKEN = "TOKEN"
        @JvmStatic protected val errorParameterResult = Result(1, "invalid parameter", null)
        @JvmStatic protected fun testParameter(vararg strs: Any?): Boolean {
            for (s in strs) if (s == null) return false
            return true
        }
    }

}
