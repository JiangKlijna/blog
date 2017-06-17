package com.jiangKlijna.web.control

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import com.jiangKlijna.web.service.UserService

@RequestMapping("/user")
@Controller
class UserControl : BaseControl() {

    @Resource(name = "userService")
    var us: UserService? = null

    @RequestMapping("/regist.json")
    fun regist(username: String, password: String, action: String) {
        val re = if (BaseControl.testParameter(username, password, action)) us!!.regist(username, password) else BaseControl.errorParameterResult
    }

    @RequestMapping("/login.json")
    fun login(username: String, password: String, action: String) {
    }

    @RequestMapping("/logout.json")
    fun logout(username: String, password: String, action: String) {
    }



}
