package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.FollowSubject
import org.apache.ibatis.annotations.Param

interface FollowSubjectMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun deleteByPrimaryKey(id: Int?): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun insert(record: FollowSubject): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun insertSelective(record: FollowSubject): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun selectByPrimaryKey(id: Int?): FollowSubject

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun updateByPrimaryKeySelective(record: FollowSubject): Int

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table blog_follow_subject

	 * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
	 */
	fun updateByPrimaryKey(record: FollowSubject): Int

	fun findByFromTo(@Param("fromuser") fromuser: Int, @Param("tosubject") tosubject: Int): FollowSubject?
}