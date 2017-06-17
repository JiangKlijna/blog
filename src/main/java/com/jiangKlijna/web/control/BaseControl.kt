package com.jiangKlijna.web.control

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import org.springframework.web.bind.annotation.ModelAttribute

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.app.Result

open class BaseControl : ContextWrapper() {

    protected var request: HttpServletRequest? = null
    protected var response: HttpServletResponse? = null
    protected var session: HttpSession? = null

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
        @JvmStatic protected val TOKEN = "TOKEN"

        @JvmStatic protected val errorParameterResult = Result(1, "invalid parameter", "")

        @JvmStatic protected fun testParameter(vararg strs: String?): Boolean {
            for (s in strs) if (s == null || s.isEmpty()) return false
            return true
        }
    }

}
