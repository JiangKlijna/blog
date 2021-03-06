package com.jiangKlijna.web.bean

data class Result(
		var code: Int = 0,
		var message: String? = null,
		var data: Any? = null
) {

	fun setCode(code: Int): Result = apply {
		this.code = code
	}

	fun setMessage(message: String): Result = apply {
		this.message = message
	}

	fun setData(data: Any): Result = apply {
		this.data = data
	}

	fun isSucess(): Boolean = code == 0

	companion object {
		const val FAILURE_LOGIN = "登陆失败"
		const val FAILURE_REGIST = "注册失败"
		const val FAILURE_PUBLISH = "发布失败"
		const val FAILURE_DELETE = "删除失败"
		const val FAILURE_SEARCH = "查询失败"
		const val FAILURE_FOLLOW = "关注失败"
		const val FAILURE_LIKE = "点赞失败"

		const val SUCCESS_LOGIN = "登陆成功"
		const val SUCCESS_REGIST = "注册成功"
		const val SUCCESS_PUBLISH = "发布成功"
		const val SUCCESS_DELETE = "删除成功"
		const val SUCCESS_SEARCH = "查询成功"
		const val SUCCESS_FOLLOW = "关注成功"
		const val SUCCESS_LIKE = "点赞成功"
	}
}
