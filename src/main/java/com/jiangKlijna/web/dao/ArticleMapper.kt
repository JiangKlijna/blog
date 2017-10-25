package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.Article
import com.jiangKlijna.web.bean.VArticle
import org.apache.ibatis.annotations.Param

interface ArticleMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun deleteByPrimaryKey(id: Int?): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun insert(record: Article): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun insertSelective(record: Article): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun selectByPrimaryKey(id: Int): Article?

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun updateByPrimaryKeySelective(record: Article): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_article

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun updateByPrimaryKey(record: Article): Int

	fun listByUser(@Param("username") username: String, @Param("limit") limit: Int, @Param("offset") offset: Int): List<VArticle>

	fun listBySubject(@Param("subjectid") subjectid: Int, @Param("limit") limit: Int, @Param("offset") offset: Int): List<VArticle>

	fun listByIndex(@Param("limit") limit: Int, @Param("offset") offset: Int): List<VArticle>

	fun listBySearch(@Param("limit") limit: Int, @Param("offset") offset: Int, @Param("search") search: String): List<VArticle>

	fun listByFollow(@Param("limit") limit: Int, @Param("offset") offset: Int, @Param("username") username: String): List<VArticle>

	fun findById(id: Int): VArticle

}