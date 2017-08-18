package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.app.Result
import com.jiangKlijna.web.dao.SubjectMapper
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.SubjectService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 8/6/2017.
 */
@Service("subjectService")
class SubjectServiceImpl : BaseService(), SubjectService {

	@Resource
	private val sm: SubjectMapper? = null

	override fun findById(id: Int): Result {
		try {
			val a = sm!!.findById(id)
			return sucessResult(a)
		} catch (e: Exception) {
			return errorResult(e)
		}
	}
}