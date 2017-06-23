package com.jiangKlijna.web.bean

/**
 * Created by leil7 on 2017/6/13. ssm-blog
 */
object Create {
    @JvmStatic fun user(username: String, password: String): User {
        val u = User()
        u.id = 0
        u.username = username
        u.password = password
        u.numberofwords = 0
        u.avatar = "static/img/user.png" //TODO
        u.createtime = System.currentTimeMillis()
        return u
    }

    @JvmStatic fun subject(title: String, touser: Int): Subject {
        val s = Subject()
        s.id = 0
        s.title = title
        s.createtime = System.currentTimeMillis()
        return s
    }

    @JvmStatic fun article(content: String, title: String, subjectid: Int, userid: Int): Article {
        val a = Article()
        a.id = 0
        a.content = content
        a.title = title
        a.userid = userid
        a.subjectid = subjectid
        a.seenumber = 0
        a.favoritenumber = 0
        a.createtime = System.currentTimeMillis()
        return a
    }

    @JvmStatic fun comment(content: String, articleid: Int, userid: Int): Comment {
        val c = Comment()
        c.id = 0
        c.content = content
        c.articleid = articleid
        c.userid = userid
        c.createtime = System.currentTimeMillis()
        return c
    }

    @JvmStatic fun followSubject(fromuser: Int, tosubject: Int): FollowSubject {
        val fs = FollowSubject()
        fs.id = 0
        fs.fromuser = fromuser
        fs.tosubject = tosubject
        fs.createtime = System.currentTimeMillis()
        return fs
    }

    @JvmStatic fun followUser(fromuser: Int, touser: Int): FollowUser {
        val fu = FollowUser()
        fu.id = 0
        fu.fromuser = fromuser
        fu.touser = touser
        fu.createtime = System.currentTimeMillis()
        return fu
    }
}
