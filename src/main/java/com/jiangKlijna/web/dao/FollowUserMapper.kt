package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.FollowUser
import com.jiangKlijna.web.bean.VFollowUser
import org.apache.ibatis.annotations.Param

interface FollowUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun deleteByPrimaryKey(id: Int?): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun insert(record: FollowUser): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun insertSelective(record: FollowUser): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun selectByPrimaryKey(id: Int?): FollowUser

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun updateByPrimaryKeySelective(record: FollowUser): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_follow_user

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun updateByPrimaryKey(record: FollowUser): Int

	fun findByFromTo(@Param("fromusername") fromusername: String, @Param("tousername") tousername: String): VFollowUser?

}