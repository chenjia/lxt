package com.lxt.ms.workflow.model;

import java.util.ArrayList;
import java.util.List;

public class UserSettingExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public UserSettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("NICK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("NICK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("NICK_NAME =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("NICK_NAME <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("NICK_NAME >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("NICK_NAME >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("NICK_NAME <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("NICK_NAME <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("NICK_NAME like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("NICK_NAME not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("NICK_NAME in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("NICK_NAME not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("NICK_NAME between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("NICK_NAME not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andChatMoodIsNull() {
            addCriterion("CHAT_MOOD is null");
            return (Criteria) this;
        }

        public Criteria andChatMoodIsNotNull() {
            addCriterion("CHAT_MOOD is not null");
            return (Criteria) this;
        }

        public Criteria andChatMoodEqualTo(String value) {
            addCriterion("CHAT_MOOD =", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodNotEqualTo(String value) {
            addCriterion("CHAT_MOOD <>", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodGreaterThan(String value) {
            addCriterion("CHAT_MOOD >", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodGreaterThanOrEqualTo(String value) {
            addCriterion("CHAT_MOOD >=", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodLessThan(String value) {
            addCriterion("CHAT_MOOD <", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodLessThanOrEqualTo(String value) {
            addCriterion("CHAT_MOOD <=", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodLike(String value) {
            addCriterion("CHAT_MOOD like", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodNotLike(String value) {
            addCriterion("CHAT_MOOD not like", value, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodIn(List<String> values) {
            addCriterion("CHAT_MOOD in", values, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodNotIn(List<String> values) {
            addCriterion("CHAT_MOOD not in", values, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodBetween(String value1, String value2) {
            addCriterion("CHAT_MOOD between", value1, value2, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatMoodNotBetween(String value1, String value2) {
            addCriterion("CHAT_MOOD not between", value1, value2, "chatMood");
            return (Criteria) this;
        }

        public Criteria andChatStatusIsNull() {
            addCriterion("CHAT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andChatStatusIsNotNull() {
            addCriterion("CHAT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andChatStatusEqualTo(Integer value) {
            addCriterion("CHAT_STATUS =", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotEqualTo(Integer value) {
            addCriterion("CHAT_STATUS <>", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusGreaterThan(Integer value) {
            addCriterion("CHAT_STATUS >", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHAT_STATUS >=", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusLessThan(Integer value) {
            addCriterion("CHAT_STATUS <", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusLessThanOrEqualTo(Integer value) {
            addCriterion("CHAT_STATUS <=", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusIn(List<Integer> values) {
            addCriterion("CHAT_STATUS in", values, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotIn(List<Integer> values) {
            addCriterion("CHAT_STATUS not in", values, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusBetween(Integer value1, Integer value2) {
            addCriterion("CHAT_STATUS between", value1, value2, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CHAT_STATUS not between", value1, value2, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andHeadPicIsNull() {
            addCriterion("HEAD_PIC is null");
            return (Criteria) this;
        }

        public Criteria andHeadPicIsNotNull() {
            addCriterion("HEAD_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPicEqualTo(String value) {
            addCriterion("HEAD_PIC =", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotEqualTo(String value) {
            addCriterion("HEAD_PIC <>", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicGreaterThan(String value) {
            addCriterion("HEAD_PIC >", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_PIC >=", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLessThan(String value) {
            addCriterion("HEAD_PIC <", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLessThanOrEqualTo(String value) {
            addCriterion("HEAD_PIC <=", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicLike(String value) {
            addCriterion("HEAD_PIC like", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotLike(String value) {
            addCriterion("HEAD_PIC not like", value, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicIn(List<String> values) {
            addCriterion("HEAD_PIC in", values, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotIn(List<String> values) {
            addCriterion("HEAD_PIC not in", values, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicBetween(String value1, String value2) {
            addCriterion("HEAD_PIC between", value1, value2, "headPic");
            return (Criteria) this;
        }

        public Criteria andHeadPicNotBetween(String value1, String value2) {
            addCriterion("HEAD_PIC not between", value1, value2, "headPic");
            return (Criteria) this;
        }

        public Criteria andMenuConfigIsNull() {
            addCriterion("MENU_CONFIG is null");
            return (Criteria) this;
        }

        public Criteria andMenuConfigIsNotNull() {
            addCriterion("MENU_CONFIG is not null");
            return (Criteria) this;
        }

        public Criteria andMenuConfigEqualTo(String value) {
            addCriterion("MENU_CONFIG =", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigNotEqualTo(String value) {
            addCriterion("MENU_CONFIG <>", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigGreaterThan(String value) {
            addCriterion("MENU_CONFIG >", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_CONFIG >=", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigLessThan(String value) {
            addCriterion("MENU_CONFIG <", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigLessThanOrEqualTo(String value) {
            addCriterion("MENU_CONFIG <=", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigLike(String value) {
            addCriterion("MENU_CONFIG like", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigNotLike(String value) {
            addCriterion("MENU_CONFIG not like", value, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigIn(List<String> values) {
            addCriterion("MENU_CONFIG in", values, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigNotIn(List<String> values) {
            addCriterion("MENU_CONFIG not in", values, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigBetween(String value1, String value2) {
            addCriterion("MENU_CONFIG between", value1, value2, "menuConfig");
            return (Criteria) this;
        }

        public Criteria andMenuConfigNotBetween(String value1, String value2) {
            addCriterion("MENU_CONFIG not between", value1, value2, "menuConfig");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table lxt_user_setting
     *
     * @mbggenerated do_not_delete_during_merge Tue Jan 29 17:24:30 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table lxt_user_setting
     *
     * @mbggenerated Tue Jan 29 17:24:30 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}