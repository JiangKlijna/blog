package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

@RequestMapping("/user")
@Controller
class UserControl : BaseControl() {

    @Resource(name = "userService")
    var us: UserService? = null

    /**
     * 用户注册
     * username若已存在，则注册失败
     */
    @ResponseBody
    @RequestMapping("/regist.json", method = arrayOf(RequestMethod.POST))
    fun regist(username: String?, password: String?, action: String?): Result {
        testParameter(username, password, action).let {
            return if (it) us!!.regist(username!!, password!!).apply {
                setMessage(if (isSucess()) Result.SUCCESS_REGIST else Result.FAILURE_REGIST)
            } else errorParameterResult
        }
    }

    /**
     * 用户登录
     * 成功后，并加密username，写入session中
     */
    @ResponseBody
    @RequestMapping("/login.json", method = arrayOf(RequestMethod.POST))
    fun login(username: String?, password: String?, action: String?): Result {
        testParameter(username, password, action).let {
            return if (it) us!!.find(username!!, password!!).apply {
                if (isSucess()) {
                    session!!.setAttribute(TOKEN, getDesUtils().encrypt(username))
                    setMessage(Result.SUCCESS_LOGIN)
                } else setMessage(Result.FAILURE_LOGIN)
            } else errorParameterResult
        }
    }

    /**
     * 判断参数username和session中的username是否一致
     * 且查询数据库，username是否存在
     * 成功则，删除session中的username
     */
    @ResponseBody
    @RequestMapping("/logout.json", method = arrayOf(RequestMethod.POST))
    fun logout(username: String?): Result {
        val un = sess_username
        testParameter(username, un).let { if (!it) return errorParameterResult }
        return if (un == username) us!!.find(username!!).apply {
            if (isSucess()) session!!.removeAttribute(TOKEN)
        } else errorParameterResult
    }

	/**
	 * 判断参数tousername和session中的fromusername不能一致
     * 关注用户
	 */
	@ResponseBody
	@RequestMapping("/follow.json", method = arrayOf(RequestMethod.POST))
	fun follow(tousername: String?): Result {
		val fromusername = sess_username
		testParameter(tousername, fromusername).let { if (!it) return errorParameterResult }
        if (tousername == fromusername) return errorParameterResult
		return us!!.follow(fromusername!!, tousername!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_FOLLOW else Result.FAILURE_FOLLOW)
		}
	}

	/**
	 * 查找userid的粉丝,并且查询sess_username是否关注这些人
	 */
	@ResponseBody
	@RequestMapping("/listByFollowUser.json", method = arrayOf(RequestMethod.POST))
	fun listByFollowUser(userid: Int?): Result {
		val sess_un = sess_username
		testParameter(userid).let { if (!it) return errorParameterResult }
		return us!!.listByFollowUser(sess_un, userid!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

	/**
	 * 查找userid关注了哪些人,并且查询sess_username是否关注这些人
	 */
	@ResponseBody
	@RequestMapping("/listByUserFollow.json", method = arrayOf(RequestMethod.POST))
	fun listByUserFollow(userid: Int?): Result {
		val sess_un = sess_username
		testParameter(userid).let { if (!it) return errorParameterResult }
		return us!!.listByUserFollow(sess_un, userid!!).apply {
			setMessage(if (isSucess()) Result.SUCCESS_SEARCH else Result.FAILURE_SEARCH)
		}
	}

}
