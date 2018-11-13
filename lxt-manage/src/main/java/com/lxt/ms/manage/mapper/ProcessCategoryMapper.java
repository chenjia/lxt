package com.lxt.ms.manage.mapper;

import com.lxt.ms.manage.model.ProcessCategory;
import com.lxt.ms.manage.model.ProcessCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int countByExample(ProcessCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int deleteByExample(ProcessCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int insert(ProcessCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int insertSelective(ProcessCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    List<ProcessCategory> selectByExample(ProcessCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    ProcessCategory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByExampleSelective(@Param("record") ProcessCategory record, @Param("example") ProcessCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByExample(@Param("record") ProcessCategory record, @Param("example") ProcessCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByPrimaryKeySelective(ProcessCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_process_category
     *
     * @mbggenerated Tue Aug 07 15:10:59 CST 2018
     */
    int updateByPrimaryKey(ProcessCategory record);
}