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

}
