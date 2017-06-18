package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.Result

interface UserService {

    fun regist(username: String, password: String): Result

    fun find(username: String, password: String): Result

    fun find(username: String): Result
}

interface PageService {

    fun isLogin(username: String, password: String): Result

}