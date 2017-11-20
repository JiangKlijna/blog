package com.jiangKlijna.web.control

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.service.MessageService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@RequestMapping("/message")
@Controller
class MessageControl : BaseControl() {

    @Resource(name = "messageService")
    var ms: MessageService? = null

    /**
     * 分页查询session_name的message
     */
    @ResponseBody
    @RequestMapping("/list.json", method = arrayOf(RequestMethod.POST))
    fun list(pageNum: Int?, perPage: Int?): Result {
        val username = sess_username
        testParameter(username, pageNum, perPage).let { if (!it) return errorParameterResult }
        return ms!!.listByUser(username!!, pageNum!!, perPage!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 阅读一条message
     */
    @ResponseBody
    @RequestMapping("/read.json", method = arrayOf(RequestMethod.POST))
    fun read(messageid: Int?): Result {
        val username = sess_username
        testParameter(username, messageid).let { if (!it) return errorParameterResult }
        return ms!!.read(username!!, messageid!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

    /**
     * 统计未读message的数量
     */
    @ResponseBody
    @RequestMapping("/unread/count.json", method = arrayOf(RequestMethod.POST))
    fun unRead(): Result {
        val username = sess_username
        testParameter(username).let { if (!it) return errorParameterResult }
        return ms!!.countUnread(username!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }
}