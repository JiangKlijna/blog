package com.jiangKlijna.web.app

class Result {


    private var code: Int = 0
    private var message: String? = null
    private var data: Any? = null

    constructor() {}

    constructor(code: Int, message: String, data: Any) {
        this.code = code
        this.message = message
        this.data = data
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int): Result {
        this.code = code
        return this
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String): Result {
        this.message = message
        return this
    }

    fun getData(): Any? {
        return data
    }

    fun setData(data: Any): Result {
        this.data = data
        return this
    }

    override fun toString(): String {
        return "Result [code=$code, message=$message, data=$data]"
    }

    fun isSucess(): Boolean {
        return code == 0
    }

    companion object {
        const val FAILURE_LOGIN = "登陆失败"
        const val FAILURE_REGIST = "注册失败"

        const val SUCCESS_LOGIN = "登陆成功"
        const val SUCCESS_REGIST = "注册成功"
    }
}
