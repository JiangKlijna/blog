package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.FollowSubject
import com.jiangKlijna.web.dao.FollowSubjectMapper
import com.jiangKlijna.web.dao.SubjectMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.SubjectService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@Service("subjectService")
class SubjectServiceImpl : BaseService(), SubjectService {

	@Resource
	private val um: UserMapper? = null

	@Resource
	private val sm: SubjectMapper? = null

	@Resource
	private val fsm: FollowSubjectMapper? = null

	override fun findById(id: Int): Result {
		try {
			val a = sm!!.findById(id)
			return sucessResult(a)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun follow(subjectid: Int, username: String): Result {
		try {
			val u = um!!.findUserByName(username)
			val fs = FollowSubject(fromuser = u!!.id, tosubject = subjectid)
			fsm!!.insert(fs)
			return sucessResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}
}