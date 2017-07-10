package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.bean.Article
import com.jiangKlijna.web.service.ArticleService
import com.jiangKlijna.web.service.BaseService
import org.springframework.stereotype.Service

/**
 * Created by leil7 on 2017/6/18. blog
 */
@Service("articleService")
class ArticleServiceImpl : BaseService(), ArticleService {
    override fun publish(a: Article): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}