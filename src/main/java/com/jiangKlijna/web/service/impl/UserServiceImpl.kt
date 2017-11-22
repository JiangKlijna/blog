package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.bean.FollowUser
import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.dao.FollowUserMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService
import com.jiangKlijna.web.websocket.ChatWebSocketHandler
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service("userService")
class UserServiceImpl : BaseService(), UserService {

    @Resource
    private val um: UserMapper? = null

    @Resource
    private val fum: FollowUserMapper? = null

    @Resource(name = "ChatWebSocketHandler")
    private val handler: ChatWebSocketHandler? = null


    override fun regist(username: String, password: String): Result {
        try {
            val re = um!!.findByUsername(username) ?: return errorResult()
            if (re == 0L) {
                val u = User(username = username, password = password)
                um.insert(u)
                return sucessResult()
            }
            return errorResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun find(username: String, password: String): Result {
        try {
            val u = User(username = username, password = password)
            val re = um!!.findByUser(u) ?: return errorResult()
            return if (re > 0) sucessResult() else errorResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun find(username: String): Result {
        try {
            val re = um!!.findByUsername(username) ?: return errorResult()
            return if (re > 0) sucessResult() else errorResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun get(username: String): Result {
        try {
            val re = um!!.findUserByName(username) ?: return errorResult()
            return sucessResult(re)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun getView(username: String): Result {
        try {
            val re = um!!.getView(username) ?: return errorResult()
            return sucessResult(re)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun follow(fromusername: String, tousername: String): Result {
        try {
            val fu = fum!!.findByFromTo(fromusername, tousername)
            if (fu == null) {
                val fromu = um!!.findUserByName(fromusername)!!
                val tou = um.findUserByName(tousername)!!
                fum.insert(FollowUser(fromuser = fromu.id, touser = tou.id))
                handler!!.publish(0, fromu.id, arrayListOf(tou.id))
                return sucessResult(1)
            } else {
                fum.deleteByPrimaryKey(fu.id)
                return sucessResult(-1)
            }
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun isFollow(fromusername: String, tousername: String): Result {
        try {
            val fu = fum!!.findByFromTo(fromusername, tousername)
            return sucessResult(fu != null)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun listByFollowUser(sess_username: String?, userid: Int): Result {
        try {
            val sess_userid =
                    if (sess_username == null) -1
                    else um!!.findUserByName(sess_username).let {
                        if (it == null) -1 else it.id
                    }
            val list = um!!.listByFollowUser(sess_userid, userid)
            return sucessResult(list)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun listByUserFollow(sess_username: String?, userid: Int): Result {
        try {
            val sess_userid =
                    if (sess_username == null) -1
                    else um!!.findUserByName(sess_username).let {
                        if (it == null) -1 else it.id
                    }
            val list = um!!.listByUserFollow(sess_userid, userid)
            return sucessResult(list)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun idListByFollowUserId(userid: Int): Result {
        try {
            val re = fum!!.idListByFollowUserId(userid)
            return sucessResult(re)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }
}
