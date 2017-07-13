package com.jiangKlijna.web.app

data class Result(
        var code: Int = 0,
        var message: String? = null,
        var data: Any? = null
) {

    fun setCode(code: Int): Result {
        this.code = code
        return this
    }

    fun setMessage(message: String): Result {
        this.message = message
        return this
    }

    fun setData(data: Any): Result {
        this.data = data
        return this
    }

    fun isSucess(): Boolean {
        return code == 0
    }

    companion object {
        const val FAILURE_LOGIN = "登陆失败"
        const val FAILURE_REGIST = "注册失败"
        const val FAILURE_PUBLISH = "发布成功"

        const val SUCCESS_LOGIN = "登陆成功"
        const val SUCCESS_REGIST = "注册成功"
        const val SUCCESS_PUBLISH = "发布成功"
    }
}
