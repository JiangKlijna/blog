package com.jiangKlijna.web.dao;

import com.jiangKlijna.web.bean.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated Tue Jun 13 22:31:48 GMT+08:00 2017
     */
    int updateByPrimaryKey(User record);
}