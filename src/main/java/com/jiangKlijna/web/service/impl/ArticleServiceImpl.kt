package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Article
import com.jiangKlijna.web.bean.Comment
import com.jiangKlijna.web.bean.Subject
import com.jiangKlijna.web.bean.toArticle
import com.jiangKlijna.web.dao.*
import com.jiangKlijna.web.service.ArticleService
import com.jiangKlijna.web.service.BaseService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by leil7 on 2017/6/18. blog
 */
@Service("articleService")
class ArticleServiceImpl : BaseService(), ArticleService {

	@Resource
	private val um: UserMapper? = null

	@Resource
	private val am: ArticleMapper? = null

	@Resource
	private val sm: SubjectMapper? = null

	override fun publish(content: String, preview: String, title: String, subject: String, username: String, numberofwords: Long): Result {
		try {
			val u = um!!.findUserByName(username)
			var s = sm!!.findSubjectByTitle(subject)
			if (s == null) {
				s = Subject(title = subject)
				sm.insert(s)
			}
			val a = Article(content = content, preview = preview, title = title, subjectid = s.id, userid = u!!.id, numberofwords = numberofwords)
			am!!.insert(a)
			return sucessResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun delete(id: Int, username: String): Result {
		try {
			val u = um!!.findUserByName(username)
			val a = am!!.selectByPrimaryKey(id)
			if (u!!.id == a!!.userid) {
				am.deleteByPrimaryKey(a.id)
				return sucessResult()
			}
			return errorResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun findById(id: Int): Result {
		try {
			val a = am!!.findById(id)
			am.updateByPrimaryKey(a.toArticle().apply { seenumber++ })
			return sucessResult(a)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listByUser(username: String, pageNum: Int, perPage: Int): Result {
		try {
			val list = am!!.listByUser(username, perPage, perPage * pageNum)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listBySubject(subjectid: Int, pageNum: Int, perPage: Int): Result {
		try {
			val list = am!!.listBySubject(subjectid, perPage, perPage * pageNum)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listByIndex(pageNum: Int, perPage: Int): Result {
		try {
			val list = am!!.listByIndex(perPage, perPage * pageNum)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listBySearch(pageNum: Int, perPage: Int, search: String): Result {
		try {
			val list = am!!.listBySearch(perPage, perPage * pageNum, search)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun listByFollow(pageNum: Int, perPage: Int, username: String): Result {
		try {
			val list = am!!.listByFollow(perPage, perPage * pageNum, username)
			return sucessResult(list)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}

	override fun follow(articleid: Int, username: String): Result {
		try {
			val a = am!!.selectByPrimaryKey(articleid)
			val u = um!!.findUserByName(username)
			if (u?.id == a?.id) return errorResult()
			a?.favoritenumber = a!!.favoritenumber + 1
			am.updateByPrimaryKey(a)
			return sucessResult()
		} catch (e: Exception) {
			return errorResult(e)
		}
	}
}