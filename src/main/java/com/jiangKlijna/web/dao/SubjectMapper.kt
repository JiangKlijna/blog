package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.Subject

interface SubjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun deleteByPrimaryKey(id: Int?): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun insert(record: Subject): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun insertSelective(record: Subject): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun selectByPrimaryKey(id: Int?): Subject

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun updateByPrimaryKeySelective(record: Subject): Int

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_subject

     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    fun updateByPrimaryKey(record: Subject): Int
}