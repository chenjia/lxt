package com.lxt.ms.workflow.graph.entity;

public class Workflow {
	private MxCell mxCell = new MxCell();
	private String label;
	private String nodeType;
	private String workflowId;
	private String categoryId;
	private String workflowKey;
	private String version;
	private int status;
	private int manual;
	private String startRule;
	private String beforeInEvent;
	private String afterInEvent;
	private String beforeOutEvent;
	private String afterOutEvent;
	private int launchType;
	private String launchText;
	private String launchValue;
	private String memo;
	private String href;
	private String id;

	public Workflow() {
		mxCell.setParent("0");
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MxCell getMxCell() {
		return mxCell;
	}

	public void setMxCell(MxCell mxCell) {
		this.mxCell = mxCell;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowKey() {
		return workflowKey;
	}

	public void setWorkflowKey(String workflowKey) {
		this.workflowKey = workflowKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}

	public String getStartRule() {
		return startRule;
	}

	public void setStartRule(String startRule) {
		this.startRule = startRule;
	}

	public String getBeforeInEvent() {
		return beforeInEvent;
	}

	public void setBeforeInEvent(String beforeInEvent) {
		this.beforeInEvent = beforeInEvent;
	}

	public String getAfterInEvent() {
		return afterInEvent;
	}

	public void setAfterInEvent(String afterInEvent) {
		this.afterInEvent = afterInEvent;
	}

	public String getBeforeOutEvent() {
		return beforeOutEvent;
	}

	public void setBeforeOutEvent(String beforeOutEvent) {
		this.beforeOutEvent = beforeOutEvent;
	}

	public String getAfterOutEvent() {
		return afterOutEvent;
	}

	public void setAfterOutEvent(String afterOutEvent) {
		this.afterOutEvent = afterOutEvent;
	}

	public int getLaunchType() {
		return launchType;
	}

	public void setLaunchType(int launchType) {
		this.launchType = launchType;
	}

	public String getLaunchText() {
		return launchText;
	}

	public void setLaunchText(String launchText) {
		this.launchText = launchText;
	}

	public String getLaunchValue() {
		return launchValue;
	}

	public void setLaunchValue(String launchValue) {
		this.launchValue = launchValue;
	}

	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
