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

    @ResponseBody
    @RequestMapping("/regist.json", method = arrayOf(RequestMethod.POST))
    fun regist(username: String?, password: String?, action: String?): Result {
        testParameter(username, password, action).let {
            return if (it) us!!.regist(username!!, password!!).apply {
                setMessage(if (isSucess()) Result.SUCCESS_REGIST else Result.FAILURE_REGIST)
            } else errorParameterResult
        }
    }

    @ResponseBody
    @RequestMapping("/login.json", method = arrayOf(RequestMethod.POST))
    fun login(username: String?, password: String?, action: String?): Result {
        testParameter(username, password, action).let {
            return if (it) us!!.find(username!!, password!!).apply {
                if (isSucess()) {
                    session!!.setAttribute("un", getDesUtils().encrypt(username))
                    setMessage(Result.SUCCESS_LOGIN)
                } else setMessage(Result.FAILURE_LOGIN)
            } else errorParameterResult
        }
    }

    @ResponseBody
    @RequestMapping("/logout.json", method = arrayOf(RequestMethod.POST))
    fun logout(username: String?): Result {
        val un = session!!.getAttribute("un") as String?
        testParameter(username, un).let { if (!it) return errorParameterResult }
        return getDesUtils().decrypt(session!!.getAttribute("un") as String).equals(username).let {
            if (it) us!!.find(username!!).apply {
                if (isSucess()) session!!.removeAttribute("un")
            } else errorParameterResult
        }
    }

    //    @Resource(name = "redisTemplate")
    //    private RedisTemplate<String, User> redisTemplate;
    //    @RequestMapping("/index.do")
    //    public String reids(Model m) {
    //        User u0 = new User(10, "test", "test");
    //        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
    //        valueOper.set(u0.getId() + "", u0);
    //        User u1 = valueOper.get(u0.getId() + "");
    //        System.out.println(u0 == u1);
    //        System.out.println(u0.toString());
    //        System.out.println(u1.toString());
    //        return "index";
    //    }


}
