package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.Result

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
}