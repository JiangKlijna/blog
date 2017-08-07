package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Comment
import com.jiangKlijna.web.dao.CommentMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.CommentService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@Service("commentService")
class CommentServiceImpl : BaseService(), CommentService {

	@Resource
	private val um: UserMapper? = null

	@Resource
	private val cm: CommentMapper? = null

	override fun write(username: String, articleid: Int, content: String): Result {
		try {
			val u = um!!.findUserByName(username)
			val c = Comment(userid = u!!.id, content = content, articleid = articleid)
			cm!!.insert(c)
			return sucessResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listByArticle(articleid: Int, pageNum: Int, perPage: Int): Result {
		try {
			val list = cm!!.listByArticle(articleid, perPage, perPage * pageNum)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}
}