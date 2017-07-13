package com.jiangKlijna.web.bean

/**
 * Created by leil7 on 2017/7/13. blog
 */
data class Article(
        var id: Int? = null,
        var title: String? = null,
        var content: String? = null,
        var userid: Int? = null,
        var subjectid: Int? = null,
        var favoritenumber: Int? = null,
        var seenumber: Int? = null,
        var createtime: Long = System.currentTimeMillis()
)

data class Comment(
        var id: Int? = null,
        var content: String? = null,
        var userid: Int? = null,
        var articleid: Int? = null,
        var createtime: Long = System.currentTimeMillis()
)

data class FollowSubject(
        var id: Int? = null,
        var fromuser: Int? = null,
        var tosubject: Int? = null,
        var createtime: Long = System.currentTimeMillis()
)

data class FollowUser(
        var id: Int? = null,
        var fromuser: Int? = null,
        var touser: Int? = null,
        var createtime: Long = System.currentTimeMillis()
)

data class Subject(
        var id: Int? = null,
        var title: String? = null,
        var createtime: Long = System.currentTimeMillis()
)

data class User(
        var id: Int? = null,
        var username: String? = null,
        var password: String? = null,
        var avatar: String? = null,
        var numberofwords: Long? = null,
        var createtime: Long = System.currentTimeMillis()
)
