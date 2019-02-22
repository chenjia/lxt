package com.lxt.ms.workflow.mapper;

import com.lxt.ms.workflow.model.Page;
import com.lxt.ms.workflow.model.PageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int countByExample(PageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int deleteByExample(PageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int deleteByPrimaryKey(String pageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int insert(Page record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int insertSelective(Page record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    List<Page> selectByExample(PageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    Page selectByPrimaryKey(String pageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") Page record, @Param("example") PageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int updateByExample(@Param("record") Page record, @Param("example") PageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int updateByPrimaryKeySelective(Page record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page
     *
     * @mbggenerated Thu Feb 14 11:30:52 CST 2019
     */
    int updateByPrimaryKey(Page record);
}