package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.dao.MessageMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.MessageService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 11/9/2017.
 */
@Service("messageService")
class MessageServiceImpl : BaseService(), MessageService {

    @Resource
    private val um: UserMapper? = null

    @Resource
    private val mm: MessageMapper? = null

    override fun listByUser(username: String, pageNum: Int, perPage: Int): Result {
        try {
            val list = mm!!.listByUser(username, perPage, perPage * pageNum)
            return sucessResult(list)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun read(username: String, messageid: Int): Result {
        try {
            val u = um!!.findUserByName(username) ?: return errorResult()
            val m = mm?.selectByPrimaryKey(messageid) ?: return errorResult()
            if (m.touser == u.id) {
                m.isread = true
                mm.updateByPrimaryKey(m)
                return sucessResult()
            } else return errorResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun countUnread(username: String): Result {
        try {
            val u = um!!.findUserByName(username) ?: return errorResult()
            val re = mm!!.countUnread(u.id)
            return sucessResult(re)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }
}