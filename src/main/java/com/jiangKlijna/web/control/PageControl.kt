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

    var sess_username: String? = null
        get() = session!!.getAttribute(TOKEN).let {
            if (it != null) getDesUtils().decrypt(it as String) else null
        }
    /**
     * 判断用户是否登陆
     * 通过session存入的加密后的username
     * 并通过数据库查询
     */
    var isLogin: Boolean = false
        get() = sess_username.let {
            return if (it != null) us!!.find(it).isSucess() else false
        }

    @Resource(name = "userService")
    var us: UserService? = null

    /**
     * 主頁
     */
    @RequestMapping("index.do")
    fun index(m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "index"
    }

    /**
     * 发现
     */
    @RequestMapping("explore.do")
    fun explore(m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "index"
    }

    /**
     * 搜索
     */
    @RequestMapping("search.do")
    fun search(query: String, m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "index"
    }

    /**
     * 写文章
     */
    @RequestMapping("write.do")
    fun write(m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "write"
    }


    /**
     * 如果用户已登录则重定向到index主页
     */
    @RequestMapping("sign.do")
    fun sign(): String = if (isLogin) "redirect:index.do" else "sign"

}
