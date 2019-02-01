package com.lxt.ms.workflow.mapper;

import com.lxt.ms.workflow.model.ChatFriend;
import com.lxt.ms.workflow.model.ChatFriendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatFriendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int countByExample(ChatFriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int deleteByExample(ChatFriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int insert(ChatFriend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int insertSelective(ChatFriend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    List<ChatFriend> selectByExample(ChatFriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    ChatFriend selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") ChatFriend record, @Param("example") ChatFriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int updateByExample(@Param("record") ChatFriend record, @Param("example") ChatFriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int updateByPrimaryKeySelective(ChatFriend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_chat_friend
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    int updateByPrimaryKey(ChatFriend record);
}