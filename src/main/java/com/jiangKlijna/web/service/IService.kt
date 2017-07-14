package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Article

interface UserService {
    //注册用户
    fun regist(username: String, password: String): Result

    //根据用户名和密码查找用户是否存在
    fun find(username: String, password: String): Result

    //根据用户名查找用户是否存在
    fun find(username: String): Result

    //根据用户名查找用户
    fun get(username: String): Result
}

interface ArticleService {
    //发布一篇文章
    fun publish(content: String, title: String, subject: String, username: String): Result

    //删除一篇文章
    fun delete(id: Int, username: String): Result
}