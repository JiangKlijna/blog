package com.jiangKlijna.web.control

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.service.SubjectService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@RequestMapping("/subject")
@Controller
class SubjectControl : BaseControl() {

	@Resource(name = "subjectService")
	var ss: SubjectService? = null

	/**
	 *  关注主题
	 */
	@ResponseBody
	@RequestMapping("/follow.json", method = arrayOf(RequestMethod.POST))
	fun followSubject(subjectid: Int?): Result {
		val un = sess_username
		testParameter(un, subjectid).let { if (!it) return errorParameterResult }
		return ss!!.follow(subjectid!!, un!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_FOLLOW else Result.FAILURE_FOLLOW)
		}
	}

	/**
	 * 分页查询index页面所有的主题
	 */
	@ResponseBody
	@RequestMapping("/listByIndex.json", method = arrayOf(RequestMethod.POST))
	fun listByIndex(pageNum: Int?, perPage: Int?): Result {
		testParameter(pageNum, perPage).let { if (!it) return errorParameterResult }
		return ss!!.listByIndex(pageNum!!, perPage!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

	/**
	 * 分页查询search页面所有的主题
	 */
	@ResponseBody
	@RequestMapping("/listBySearch.json", method = arrayOf(RequestMethod.POST))
	fun listBySearch(pageNum: Int?, perPage: Int?, search: String?): Result {
		testParameter(pageNum, perPage, search).let { if (!it) return errorParameterResult }
		return ss!!.listBySearch(pageNum!!, perPage!!, search!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

	/**
	 * 分页查询follow页面所有的主题
	 */
	@ResponseBody
	@RequestMapping("/listByFollow.json", method = arrayOf(RequestMethod.POST))
	fun listByFollow(pageNum: Int?, perPage: Int?): Result {
		val username = sess_username
		testParameter(username, pageNum, perPage).let { if (!it) return errorParameterResult }
		return ss!!.listByFollow(pageNum!!, perPage!!, username!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}
}