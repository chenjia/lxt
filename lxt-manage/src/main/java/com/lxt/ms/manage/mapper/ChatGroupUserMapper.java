package com.lxt.ms.manage.mapper;

import com.lxt.ms.manage.model.ChatGroupUserExample;
import com.lxt.ms.manage.model.ChatGroupUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatGroupUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int countByExample(ChatGroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int deleteByExample(ChatGroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int deleteByPrimaryKey(ChatGroupUserKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int insert(ChatGroupUserKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int insertSelective(ChatGroupUserKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    List<ChatGroupUserKey> selectByExample(ChatGroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByExampleSelective(@Param("record") ChatGroupUserKey record, @Param("example") ChatGroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_group_user
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByExample(@Param("record") ChatGroupUserKey record, @Param("example") ChatGroupUserExample example);
}