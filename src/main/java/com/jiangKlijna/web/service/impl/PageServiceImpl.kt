package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.PageService
import org.springframework.stereotype.Service

/**
 * Created by leil7 on 2017/6/18. blog
 */
@Service("pageService")
class PageServiceImpl : BaseService(), PageService {
    override fun isLogin(username: String): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}