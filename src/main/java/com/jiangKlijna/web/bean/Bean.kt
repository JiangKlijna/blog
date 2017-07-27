package com.jiangKlijna.web.bean

import java.lang.System.currentTimeMillis

/**
 * Created by leil7 on 2017/7/13. blog
 */
data class Article(
		var id: Int = 0,
		var title: String? = null,
		var content: String? = null,
		var userid: Int? = null,
		var subjectid: Int? = null,
		var favoritenumber: Int = 0,
		var seenumber: Int = 0,
		var numberofwords: Long = 0,
		var createtime: Long = currentTimeMillis()
)

data class Comment(
		var id: Int = 0,
		var content: String? = null,
		var userid: Int? = null,
		var articleid: Int? = null,
		var createtime: Long = currentTimeMillis()
)

data class FollowSubject(
		var id: Int = 0,
		var fromuser: Int? = null,
		var tosubject: Int? = null,
		var createtime: Long = currentTimeMillis()
)

data class FollowUser(
		var id: Int = 0,
		var fromuser: Int? = null,
		var touser: Int? = null,
		var createtime: Long = currentTimeMillis()
)

data class Subject(
		var id: Int = 0,
		var title: String? = null,
		var createtime: Long = currentTimeMillis()
)

data class User(
		var id: Int = 0,
		var username: String? = null,
		var password: String? = null,
		var createtime: Long = currentTimeMillis()
)

data class Message(
		var id: Int = 0,
		var fromuser: Int? = null,
		var touser: Int? = null,
		var flag: Int = 0,
		var isread: Boolean = false,
		var createtime: Long = currentTimeMillis()
)