package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.ArticleService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by leil7 on 2017/7/11. blog
 */
@RequestMapping("/article")
@Controller
class ArticleControl : BaseControl() {

	@Resource(name = "articleService")
	var `as`: ArticleService? = null

	/**
	 * 发表文章
	 * 顺便给所有订阅此人和订阅主题的用户推送消息
	 */
	@ResponseBody
	@RequestMapping("/publish.json", method = arrayOf(RequestMethod.POST))
	fun publish(content: String?, preview: String?, title: String?, subject: String?, numberofwords: Long?): Result {
		val username = sess_username
		if (!testParameter(content, preview, title, subject, username, numberofwords)) return errorParameterResult
		return `as`!!.publish(content!!, preview!!, title!!, subject!!, username!!, numberofwords!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_PUBLISH else Result.FAILURE_PUBLISH)
		}
	}

	/**
	 * 删除文章，直接从数据库中删除，不用推送消息
	 */
	@ResponseBody
	@RequestMapping("/delete.json", method = arrayOf(RequestMethod.POST))
	fun delete(id: Int?, username: String?): Result {
		val un = sess_username
		testParameter(id, username, un).let { if (!it) return errorParameterResult }
		return if (un == username) `as`!!.delete(id!!, username!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_DELETE else Result.FAILURE_DELETE)
		} else errorParameterResult
	}

	/**
	 * 通过id查询文章
	 */
	@ResponseBody
	@RequestMapping("/findById.json", method = arrayOf(RequestMethod.POST))
	fun findById(id: Int?): Result {
		testParameter(id).let { if (!it) return errorParameterResult }
		return `as`!!.findById(id!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

	/**
	 * 分页查询user的文章
	 */
	@ResponseBody
	@RequestMapping("/listByUser.json", method = arrayOf(RequestMethod.POST))
	fun listByUser(username: String?, pageNum: Int?, perPage: Int?): Result {
		testParameter(username, pageNum, perPage).let { if (!it) return errorParameterResult }
		return `as`!!.listByUser(username!!, pageNum!!, perPage!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

	/**
	 *  喜欢文章(点赞),给作者推送消息
	 */
	@ResponseBody
	@RequestMapping("/follow.json", method = arrayOf(RequestMethod.POST))
	fun followArticle(articleid: Int?): Result {
		val un = sess_username
		testParameter(un, articleid).let { if (!it) return errorParameterResult }
		return `as`!!.follow(articleid!!, un!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_FOLLOW else Result.FAILURE_FOLLOW)
		}
	}


}