package com.lxt.ms.manage.model;

import java.util.Date;

public class ChatGroup {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.GROUP_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private String groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.USER_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.GROUP_NAME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.SORT_SEQ
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private Integer sortSeq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.INSERT_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private Date insertTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_chat_group.UPDATE_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.GROUP_ID
     *
     * @return the value of lxt_chat_group.GROUP_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.GROUP_ID
     *
     * @param groupId the value for lxt_chat_group.GROUP_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.USER_ID
     *
     * @return the value of lxt_chat_group.USER_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.USER_ID
     *
     * @param userId the value for lxt_chat_group.USER_ID
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.GROUP_NAME
     *
     * @return the value of lxt_chat_group.GROUP_NAME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.GROUP_NAME
     *
     * @param groupName the value for lxt_chat_group.GROUP_NAME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.SORT_SEQ
     *
     * @return the value of lxt_chat_group.SORT_SEQ
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public Integer getSortSeq() {
        return sortSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.SORT_SEQ
     *
     * @param sortSeq the value for lxt_chat_group.SORT_SEQ
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setSortSeq(Integer sortSeq) {
        this.sortSeq = sortSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.INSERT_TIME
     *
     * @return the value of lxt_chat_group.INSERT_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.INSERT_TIME
     *
     * @param insertTime the value for lxt_chat_group.INSERT_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_chat_group.UPDATE_TIME
     *
     * @return the value of lxt_chat_group.UPDATE_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_chat_group.UPDATE_TIME
     *
     * @param updateTime the value for lxt_chat_group.UPDATE_TIME
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}