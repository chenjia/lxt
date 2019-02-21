package com.lxt.ms.manage.mapper;

import com.lxt.ms.manage.model.Organization;
import com.lxt.ms.manage.model.OrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int countByExample(OrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int deleteByExample(OrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int deleteByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int insert(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int insertSelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    List<Organization> selectByExample(OrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    Organization selectByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lxt_organization
     *
     * @mbggenerated Thu Feb 21 20:20:35 CST 2019
     */
    int updateByPrimaryKey(Organization record);
}