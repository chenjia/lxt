package com.lxt.ms.workflow.mapper;

import com.lxt.ms.workflow.model.PageComponent;
import com.lxt.ms.workflow.model.PageComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageComponentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int countByExample(PageComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int deleteByExample(PageComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int insert(PageComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int insertSelective(PageComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    List<PageComponent> selectByExample(PageComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    PageComponent selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int updateByExampleSelective(@Param("record") PageComponent record, @Param("example") PageComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int updateByExample(@Param("record") PageComponent record, @Param("example") PageComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int updateByPrimaryKeySelective(PageComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_page_component
     *
     * @mbggenerated Wed Mar 20 17:24:04 CST 2019
     */
    int updateByPrimaryKey(PageComponent record);
}