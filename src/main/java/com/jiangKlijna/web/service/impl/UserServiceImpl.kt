package com.jiangKlijna.web.service.impl

import org.springframework.stereotype.Service

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.FollowUser
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.dao.FollowUserMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService

import javax.annotation.Resource

@Service("userService")
class UserServiceImpl : BaseService(), UserService {

	@Resource
	private val um: UserMapper? = null

	@Resource
	private val fum: FollowUserMapper? = null


	override fun regist(username: String, password: String): Result {
		try {
			val u = User(username = username, password = password)
			um!!.insert(u)
			return sucessResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun find(username: String, password: String): Result {
		try {
			val u = User(username = username, password = password)
			val re = um!!.findByUser(u)
			return (re != null && re > 0).let {
				if (it) sucessResult() else errorResult()
			}
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun find(username: String): Result {
		try {
			val re = um!!.findByUsername(username)
			return (re != null && re > 0).let {
				if (it) sucessResult() else errorResult()
			}
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun get(username: String): Result {
		try {
			val re = um!!.findUserByName(username)
			return if (re != null) sucessResult(re) else errorResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun getView(username: String): Result {
		try {
			val re = um!!.getView(username)
			return if (re != null) sucessResult(re) else errorResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun follow(fromusername: String, tousername: String): Result {
		try {
			var fu = fum!!.findByFromTo(fromusername, tousername)
			if (fu == null) {
				val fromu = um!!.findUserByName(fromusername)!!
				val tou = um.findUserByName(tousername)!!
				fu = FollowUser(fromuser = fromu.id, touser = tou.id)
				fum.insert(fu)
				return sucessResult(1)
			} else {
				fum.deleteByPrimaryKey(fu.id)
				return sucessResult(-1)
			}
		} catch (e: Exception) {
			return errorResult(e)
		}
	}
}
