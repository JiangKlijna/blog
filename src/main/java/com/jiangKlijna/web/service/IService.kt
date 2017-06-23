package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.Result

interface UserService {
    //注册用户
    fun regist(username: String, password: String): Result

    //根据用户名和密码查找用户
    fun find(username: String, password: String): Result

    //根据用户名查找用户
    fun find(username: String): Result
}

interface PageService {
    //根据用户名查找用户
    fun isLogin(username: String): Result

}