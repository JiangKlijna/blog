package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.UserService
import org.apache.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.annotation.Resource

/**
 * Created by leil7 on 2017/6/4. ssm-blog
 */
@Controller
@RequestMapping("/")
class PageControl : BaseControl() {

    var isLogin: Boolean = false
        get() = session!!.getAttribute("un").let {
            if (it != null) {
                val un = getDesUtils().decrypt(it as String)
                return us!!.find(un).isSucess()
            }
            return false
        }

    @Resource(name = "userService")
    var us: UserService? = null

    @RequestMapping("index.do")
    fun index(m: Model): String {
        val isLogin = isLogin
        m.addAttribute("isLogin", isLogin)


        return "index"
    }

    @RequestMapping("sign.do")
    fun sign(): String {
        return if (isLogin) "redirect:index.do" else "sign"
    }

}
