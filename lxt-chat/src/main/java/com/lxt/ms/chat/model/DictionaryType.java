package com.lxt.ms.chat.model;

public class DictionaryType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_dictionary_type.TYPE
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lxt_dictionary_type.NAME
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_dictionary_type.TYPE
     *
     * @return the value of lxt_dictionary_type.TYPE
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_dictionary_type.TYPE
     *
     * @param type the value for lxt_dictionary_type.TYPE
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lxt_dictionary_type.NAME
     *
     * @return the value of lxt_dictionary_type.NAME
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lxt_dictionary_type.NAME
     *
     * @param name the value for lxt_dictionary_type.NAME
     *
     * @mbggenerated Wed Oct 10 13:27:53 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}