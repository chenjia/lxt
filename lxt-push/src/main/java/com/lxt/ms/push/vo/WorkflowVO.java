package com.lxt.ms.push.vo;

import java.util.Date;

public class WorkflowVO {
	private String workflowId;
	private String versionNo;
	private String workflowCategoryId;
	private String categoryName;
	private String name;
	private String workflowKey;
	private String memo;
	private String graphXml;
	private String jbpmXml;
	private Integer status;
	private Integer publishStatus;
	private Date publishTime;
	private String creator;
	private Date createTime;
	private String modifyUserId;
	private Date modifyTime;
	private String deployeId;

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getWorkflowCategoryId() {
		return workflowCategoryId;
	}

	public void setWorkflowCategoryId(String workflowCategoryId) {
		this.workflowCategoryId = workflowCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkflowKey() {
		return workflowKey;
	}

	public void setWorkflowKey(String workflowKey) {
		this.workflowKey = workflowKey;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGraphXml() {
		return graphXml;
	}

	public void setGraphXml(String graphXml) {
		this.graphXml = graphXml;
	}

	public String getJbpmXml() {
		return jbpmXml;
	}

	public void setJbpmXml(String jbpmXml) {
		this.jbpmXml = jbpmXml;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getDeployeId() {
		return deployeId;
	}

	public void setDeployeId(String deployeId) {
		this.deployeId = deployeId;
	}

}
