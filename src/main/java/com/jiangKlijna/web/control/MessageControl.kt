package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
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
    @RequestMapping("/listByArticle.json", method = arrayOf(RequestMethod.POST))
    fun listByArticle(articleid: Int?, pageNum: Int?, perPage: Int?): Result {
        val username = sess_username
        testParameter(username, articleid, pageNum, perPage).let { if (!it) return errorParameterResult }
        return ms!!.listByUser(username!!, pageNum!!, perPage!!).apply {
            setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
        }
    }

}