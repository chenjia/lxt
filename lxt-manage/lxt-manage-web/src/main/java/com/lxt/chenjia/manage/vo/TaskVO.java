package com.lxt.chenjia.manage.vo;

import java.util.Date;

public class TaskVO {
	private String taskId;
	private String taskName;
	private String workflowId;
	private String workflowName;
	private String workflowVersion;
	private String nodeId;
	private String executionId;
	private Date createTime;
	private Date completeTime;
	private Long duration;
	private String prevUser;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getWorkflowId() {
		return workflowId;
	}
	
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowVersion() {
		return workflowVersion;
	}

	public void setWorkflowVersion(String workflowVersion) {
		this.workflowVersion = workflowVersion;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}
	
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public String getPrevUser() {
		return prevUser;
	}

	public void setPrevUser(String prevUser) {
		this.prevUser = prevUser;
	}
}
