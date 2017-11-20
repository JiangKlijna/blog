package com.jiangKlijna.web.control

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.service.ArticleService
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
    val `as`: ArticleService? = null

    /**
     * 发表文章
     * @message type 3 给所有订阅此人和订阅主题的用户推送消息
     */
    @ResponseBody
    @RequestMapping("/publish.json", method = arrayOf(RequestMethod.POST))
    fun publish(content: String?, preview: String?, title: String?, subject: String?, numberofwords: Long?): Result {
        val username = sess_username
        if (!testParameter(content, preview, title, subject, username, numberofwords)) return errorParameterResult
        return `as`!!.publish(content!!, preview!!, title!!, subject!!, username!!, numberofwords!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_PUBLISH else Result.FAILURE_PUBLISH)
        }
    }

    /**
     * 删除文章，直接从数据库中删除
     */
    @ResponseBody
    @RequestMapping("/delete.json", method = arrayOf(RequestMethod.POST))
    fun delete(id: Int?, username: String?): Result {
        val un = sess_username
        testParameter(id, username, un).let { if (!it) return errorParameterResult }
        return if (un == username) `as`!!.delete(id!!, username!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_DELETE else Result.FAILURE_DELETE)
        } else errorParameterResult
    }

    /**
     * 通过id查询文章
     */
    @ResponseBody
    @RequestMapping("/findById.json", method = arrayOf(RequestMethod.POST))
    fun findById(id: Int?): Result {
        testParameter(id).let { if (!it) return errorParameterResult }
        return `as`!!.findById(id!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 分页查询user的文章
     */
    @ResponseBody
    @RequestMapping("/listByUser.json", method = arrayOf(RequestMethod.POST))
    fun listByUser(username: String?, pageNum: Int?, perPage: Int?): Result {
        testParameter(username, pageNum, perPage).let { if (!it) return errorParameterResult }
        return `as`!!.listByUser(username!!, pageNum!!, perPage!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 分页查询subject的文章
     */
    @ResponseBody
    @RequestMapping("/listBySubject.json", method = arrayOf(RequestMethod.POST))
    fun listBySubject(subjectid: Int?, pageNum: Int?, perPage: Int?): Result {
        testParameter(subjectid, pageNum, perPage).let { if (!it) return errorParameterResult }
        return `as`!!.listBySubject(subjectid!!, pageNum!!, perPage!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 分页查询index页面所有的文章
     */
    @ResponseBody
    @RequestMapping("/listByIndex.json", method = arrayOf(RequestMethod.POST))
    fun listByIndex(pageNum: Int?, perPage: Int?): Result {
        testParameter(pageNum, perPage).let { if (!it) return errorParameterResult }
        return `as`!!.listByIndex(pageNum!!, perPage!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 分页查询search页面所有的文章
     */
    @ResponseBody
    @RequestMapping("/listBySearch.json", method = arrayOf(RequestMethod.POST))
    fun listBySearch(pageNum: Int?, perPage: Int?, search: String?): Result {
        testParameter(pageNum, perPage, search).let { if (!it) return errorParameterResult }
        return `as`!!.listBySearch(pageNum!!, perPage!!, search!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 分页查询follow页面所有的文章
     */
    @ResponseBody
    @RequestMapping("/listByFollow.json", method = arrayOf(RequestMethod.POST))
    fun listByFollow(pageNum: Int?, perPage: Int?): Result {
        val username = sess_username
        testParameter(username, pageNum, perPage).let { if (!it) return errorParameterResult }
        return `as`!!.listByFollow(pageNum!!, perPage!!, username!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     *  喜欢文章(点赞)
     *  @message type 2 给作者推送消息
     */
    @ResponseBody
    @RequestMapping("/follow.json", method = arrayOf(RequestMethod.POST))
    fun followArticle(articleid: Int?): Result {
        val un = sess_username
        testParameter(un, articleid).let { if (!it) return errorParameterResult }
        return `as`!!.follow(articleid!!, un!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_LIKE else Result.FAILURE_LIKE)
        }
    }


}