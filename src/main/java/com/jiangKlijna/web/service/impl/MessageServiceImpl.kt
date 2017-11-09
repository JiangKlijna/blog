package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.dao.MessageMapper
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
    private val mm: MessageMapper? = null

    override fun listByUser(username: String, pageNum: Int, perPage: Int): Result {
        try {
            val list = mm!!.listByUser(username, perPage, perPage * pageNum)
            return sucessResult(list)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }
}