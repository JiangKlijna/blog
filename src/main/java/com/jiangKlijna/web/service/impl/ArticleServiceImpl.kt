package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Article
import com.jiangKlijna.web.dao.ArticleMapper
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
    private val am: ArticleMapper? = null

    override fun publish(a: Article): Result {
        try {
            am!!.insert(a)
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e)
        }
    }

}