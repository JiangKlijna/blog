package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.bean.Result

open class BaseService : ContextWrapper() {

    protected fun sucessResult(): Result {
        return applicationContext.getBean("sucessResult", Result::class.java)
    }

    protected fun sucessResult(data: Any): Result {
        return sucessResult().setData(data)
    }

    protected fun errorResult(): Result {
        return applicationContext.getBean("errorResult", Result::class.java)
    }

    protected fun errorResult(message: String): Result {
        return applicationContext.getBean("errorResult", Result::class.java).setMessage(message)
    }

    protected fun errorResult(e: Exception): Result {
        return errorResult(e.toString())
    }
}
