package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Create
import com.jiangKlijna.web.service.ArticleService
import com.jiangKlijna.web.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by leil7 on 2017/7/11. blog
 */
@RequestMapping("/article")
@Controller
class ArticleControl : BaseControl() {

    @Resource(name = "articleService")
    var `as`: ArticleService? = null

    /**
     * 发表文章
     * 顺便给所有订阅此人和订阅主题的用户推送消息
     */
    @ResponseBody
    @RequestMapping("/publish.json", method = arrayOf(RequestMethod.POST))
    fun publish(content: String?, title: String?, subject: String?): Result {
        val username = sess_username
        if (!testParameter(content, title, subject, username)) return errorParameterResult
        return `as`!!.publish(content!!, title!!, subject!!, username!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_PUBLISH else Result.FAILURE_PUBLISH)
        }
        //val u = us!!.get(username!!)
    }

    /**
     * 删除文章，直接从数据库中删除，不用推送消息
     */
    @ResponseBody
    @RequestMapping("/delete.json", method = arrayOf(RequestMethod.POST))
    fun delete(id: Int, username: String?): Result {
        val un = sess_username
        testParameter(username, un).let { if (!it) return errorParameterResult }
        return if (un == username) `as`!!.delete(id, username!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_DELETE else Result.FAILURE_DELETE)
        } else errorParameterResult
    }

}