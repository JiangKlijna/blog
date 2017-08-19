package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.CommentService
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
}