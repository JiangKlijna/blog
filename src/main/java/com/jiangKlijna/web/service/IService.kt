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

	//通过id查询文章
	fun findById(id: Int): Result

	//分页查询user的文章
	fun listByUser(username: String, pageNum: Int, perPage: Int, size: Int): Result

}

interface CommentService {
	//写评论
	fun write(username: String, articleid: Int, content: String): Result

	//分页查询articleid的评论
	fun listByArticle(articleid: Int, pageNum: Int, perPage: Int, size: Int): Result

}