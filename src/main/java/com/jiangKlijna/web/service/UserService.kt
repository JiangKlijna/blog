package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.Result

interface UserService {

    fun regist(username: String, password: String): Result

    fun remove(id: Int): Result

    fun find(id: Int): Result
}
