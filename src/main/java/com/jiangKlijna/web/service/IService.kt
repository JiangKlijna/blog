package com.jiangKlijna.web.service

import com.jiangKlijna.web.bean.Result

interface UserService {
    //注册用户
    fun regist(username: String, password: String): Result

    //根据用户名和密码查找用户是否存在
    fun find(username: String, password: String): Result

    //根据用户名查找用户是否存在
    fun find(username: String): Result

    //根据用户名查找用户
    fun get(username: String): Result

    //获得v_blog_user视图
    fun getView(username: String): Result

    //关注一个用户
    fun follow(fromusername: String, tousername: String): Result

    //是否关注
    fun isFollow(fromusername: String, tousername: String): Result

    //查找userid的粉丝,并且查询sess_username是否关注这些人
    fun listByFollowUser(sess_username: String?, userid: Int): Result

    //查找userid关注了哪些人,并且查询sess_username是否关注这些人
    fun listByUserFollow(sess_username: String?, userid: Int): Result

    //查找谁关注了userid
    fun idListByFollowUserId(userid: Int): Result
}

interface ArticleService {
    //发布一篇文章
    fun publish(content: String, preview: String, title: String, subject: String, username: String, numberofwords: Long): Result

    //删除一篇文章
    fun delete(id: Int, username: String): Result

    //通过id查询文章, 并增加seenumber
    fun findById(id: Int): Result

    //分页查询user的文章
    fun listByUser(username: String, pageNum: Int, perPage: Int): Result

    //分页查询subject的文章
    fun listBySubject(subjectid: Int, pageNum: Int, perPage: Int): Result

    //喜欢,点赞文章
    fun follow(articleid: Int, username: String): Result

    //分页查询index页面所有的文章
    fun listByIndex(pageNum: Int, perPage: Int): Result

    //分页查询search页面所有的文章
    fun listBySearch(pageNum: Int, perPage: Int, search: String): Result

    //分页查询follow页面所有的文章
    fun listByFollow(pageNum: Int, perPage: Int, username: String): Result
}

interface CommentService {
    //写评论
    fun write(username: String, articleid: Int, content: String): Result

    //分页查询articleid的评论
    fun listByArticle(articleid: Int, pageNum: Int, perPage: Int): Result

    //删除自己的评论
    fun delete(commentid: Int, username: String): Result
}

interface SubjectService {
    //通过id查询,主题
    fun findById(id: Int): Result

    //订阅主题, 如果以订阅则取消订阅
    fun follow(subjectid: Int, username: String): Result

    //是否订阅
    fun isFollow(subjectid: Int, username: String): Result

    //分页查询index页面所有的主题
    fun listByIndex(pageNum: Int, perPage: Int): Result

    //分页查询search页面所有的主题
    fun listBySearch(pageNum: Int, perPage: Int, search: String): Result

    //分页查询follow页面所有的主题
    fun listByFollow(pageNum: Int, perPage: Int, username: String): Result

    //查找谁关注了SubjectId
    fun idListByFollowSubjectId(subjectid: Int): Result
}

interface MessageService {
    //分页查询username的message
    fun listByUser(username: String, pageNum: Int, perPage: Int): Result

    //阅读一条message
    fun read(username: String, messageid: Int): Result

    //获得一个人的未读消息数量
    fun countUnread(username: String): Result

}