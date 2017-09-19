package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.ArticleService
import com.jiangKlijna.web.service.SubjectService
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
			if (it != null) us!!.find(it).isSucess() else false
		}

	@Resource(name = "userService")
	var us: UserService? = null

	@Resource(name = "articleService")
	var `as`: ArticleService? = null

	@Resource(name = "subjectService")
	var ss: SubjectService? = null

	/**
	 * 主頁
	 */
	@RequestMapping("index.do")
	fun index(m: Model): String {
		val isLogin = isLogin
		val username = sess_username
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("username", username)

		return "index"
	}

	/**
	 * 关注
	 */
	@RequestMapping("follow.do")
	fun follow(m: Model): String {
		val isLogin = isLogin
		val username = sess_username
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("username", username)

		return "index"
	}

	/**
	 * 搜索
	 */
	@RequestMapping("search.do")
	fun search(query: String?, m: Model): String {
		if (query == null || query == "") return "redirect:index.do"
		val isLogin = isLogin
		val username = sess_username
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("username", username)

		return "index"
	}

	/**
	 * 写文章
	 */
	@RequestMapping("write.do")
	fun write(m: Model): String = if (isLogin) {
		val username = sess_username
		m.addAttribute("isLogin", true)
		m.addAttribute("username", username)
		"write"
	} else "redirect:sign.do"

	/**
	 * 每个人的主页
	 */
	@RequestMapping("person.do")
	fun people(name: String, m: Model): String {
		val isLogin = isLogin
		val username = sess_username
		val re = us!!.getView(name)
		val isExist = re.isSucess()
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("isExist", isExist)
		m.addAttribute("username", username)
		m.addAttribute("name", name)
		m.addAttribute("vu", re.data)
		if (username != null) m.addAttribute("isFollow", us!!.isFollow(username, name).data as Boolean)
		else m.addAttribute("isFollow", false)
		return "person"
	}

	/**
	 * 文章
	 */
	@RequestMapping("article.do")
	fun article(id: Int?, m: Model): String {
		val isLogin = isLogin
		val username = sess_username
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("username", username)
		var isExist = false
		if (id != null) {
			val r = `as`!!.findById(id)
			if (r.isSucess()) {
				isExist = true
				m.addAttribute("article", r.data)
			}
		}
		m.addAttribute("isExist", isExist)
		return "article"
	}

	/**
	 * 课题
	 */
	@RequestMapping("subject.do")
	fun subject(id: Int?, m: Model): String {
		val isLogin = isLogin
		val username = sess_username
		m.addAttribute("isLogin", isLogin)
		m.addAttribute("username", username)
		var isExist = false
		if (id != null) {
			val s = ss!!.findById(id)
			if (s.isSucess()) {
				isExist = true
				m.addAttribute("subject", s.data)
			}
			val isFollow = if (username == null) false else ss!!.isFollow(id, username).data as Boolean
			m.addAttribute("isFollow", isFollow)
		}
		m.addAttribute("isExist", isExist)
		return "subject"
	}

	/**
	 * 如果用户已登录则重定向到index主页
	 */
	@RequestMapping("sign.do")
	fun sign(): String = if (isLogin) "redirect:index.do" else "sign"

}
