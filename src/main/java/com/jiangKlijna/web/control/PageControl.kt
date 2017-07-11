package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.UserService
import org.apache.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.annotation.Resource

/**
 * Created by leil7 on 2017/6/4. ssm-blog
 */
@Controller
@RequestMapping("/")
class PageControl : BaseControl() {

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
     * 每个人的主页
     */
    @RequestMapping("people.do")
    fun people(name: String, m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)
        m.addAttribute("name", name)
        return "people"
    }

    /**
     * 文章
     */
    @RequestMapping("article.do")
    fun article(id: Int?, m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "index"
    }

    /**
     * 课题
     */
    @RequestMapping("subject.do")
    fun subject(id: Int?, m: Model): String {
        val isLogin = isLogin
        var username = sess_username
        m.addAttribute("isLogin", isLogin)
        m.addAttribute("username", username)

        return "index"
    }

    /**
     * 如果用户已登录则重定向到index主页
     */
    @RequestMapping("sign.do")
    fun sign(): String = if (isLogin) "redirect:index.do" else "sign"

}
