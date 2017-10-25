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
			var fs = fsm!!.findByFromTo(fromuser = u!!.id, tosubject = subjectid)
			if (fs == null) {
				fs = FollowSubject(fromuser = u.id, tosubject = subjectid)
				fsm.insert(fs)
				return sucessResult(1)
			} else {
				fsm.deleteByPrimaryKey(fs.id)
				return sucessResult(-1)
			}
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun isFollow(subjectid: Int, username: String): Result {
		try {
			val u = um!!.findUserByName(username)
			val fs = fsm!!.findByFromTo(fromuser = u!!.id, tosubject = subjectid)
			return sucessResult(fs != null)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listByIndex(pageNum: Int, perPage: Int): Result {
		try {
			val list = sm!!.listByIndex(perPage, perPage * pageNum)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listBySearch(pageNum: Int, perPage: Int, search: String): Result {
		try {
			val list = sm!!.listBySearch(perPage, perPage * pageNum, search)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

}