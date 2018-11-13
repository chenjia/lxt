package com.lxt.ms.chat.mapper;

import com.lxt.ms.chat.model.User;
import com.lxt.ms.chat.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    User selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    int updateByPrimaryKey(User record);
}