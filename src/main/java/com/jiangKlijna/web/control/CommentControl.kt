package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.CommentService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@RequestMapping("/comment")
@Controller
class CommentControl : BaseControl() {

	@Resource(name = "commentService")
	var cs: CommentService? = null


	/**
	 * 写评论,并推送给文章作者
	 */
	@ResponseBody
	@RequestMapping("/write.json", method = arrayOf(RequestMethod.POST))
	fun writeComment(articleid: Int?, content: String?): Result {
		val un = sess_username
		testParameter(un, articleid, content).let { if (!it) return errorParameterResult }
		return cs!!.write(un!!, articleid!!, content!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_PUBLISH else Result.FAILURE_PUBLISH)
		}
	}

	/**
	 * 分页查询Article的评论
	 */
	@ResponseBody
	@RequestMapping("/listByArticle.json", method = arrayOf(RequestMethod.POST))
	fun listByArticle(articleid: Int?, pageNum: Int?, perPage: Int?): Result {
		testParameter(articleid, pageNum, perPage).let { if (!it) return errorParameterResult }
		return cs!!.listByArticle(articleid!!, pageNum!!, perPage!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}
}