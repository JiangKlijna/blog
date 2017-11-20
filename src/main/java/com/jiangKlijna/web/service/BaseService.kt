package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.bean.Result

open class BaseService : ContextWrapper() {

    protected fun sucessResult(): Result = applicationContext.getBean(SUCESS_RESULT, Result::class.java)

    protected fun sucessResult(data: Any): Result = sucessResult().setData(data)

    protected fun errorResult(): Result = applicationContext.getBean(ERROR_RESULT, Result::class.java)

    protected fun errorResult(message: String): Result = errorResult().setMessage(message)

    protected fun errorResult(e: Exception): Result = errorResult(e.toString())

    companion object {
        const val SUCESS_RESULT = "sucessResult"
        const val ERROR_RESULT = "errorResult"
    }
}
