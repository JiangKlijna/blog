package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.bean.Comment
import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.dao.ArticleMapper
import com.jiangKlijna.web.dao.CommentMapper
import com.jiangKlijna.web.dao.UserMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.CommentService
import com.jiangKlijna.web.websocket.ChatWebSocketHandler
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@Service("commentService")
class CommentServiceImpl : BaseService(), CommentService {

    @Resource
    private val um: UserMapper? = null

    @Resource
    private val cm: CommentMapper? = null

    @Resource
    private val am: ArticleMapper? = null

    @Resource(name = "ChatWebSocketHandler")
    private val handler: ChatWebSocketHandler? = null

    override fun write(username: String, articleid: Int, content: String): Result {
        try {
            val a = am!!.selectByPrimaryKey(articleid) ?: return errorResult()
            val u = um!!.findUserByName(username) ?: return errorResult()
            val c = Comment(userid = u.id, content = content, articleid = articleid)
            cm!!.insert(c)
            handler!!.publish(1, u.id, arrayListOf(a.userid!!))
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun listByArticle(articleid: Int, pageNum: Int, perPage: Int): Result {
        try {
            val list = cm!!.listByArticle(articleid, perPage, perPage * pageNum)
            return sucessResult(list)
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

    override fun delete(commentid: Int, username: String): Result {
        try {
            val u = um!!.findUserByName(username)
            val c = cm!!.selectByPrimaryKey(commentid)
            if (c.userid == u!!.id) {
                cm.deleteByPrimaryKey(commentid)
                return sucessResult()
            }
            return errorResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }
}