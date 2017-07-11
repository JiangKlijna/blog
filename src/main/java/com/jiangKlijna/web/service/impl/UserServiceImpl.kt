package com.jiangKlijna.web.service.impl

import org.springframework.stereotype.Service

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Create
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService

import javax.annotation.Resource

@Service("userService")
class UserServiceImpl : BaseService(), UserService {

    @Resource
    private val um: UserMapper? = null

    override fun regist(username: String, password: String): Result {
        try {
            val u = Create.user(username, password)
            um!!.insert(u)
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun find(username: String, password: String): Result {
        try {
            val u = Create.user(username, password)
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


}
