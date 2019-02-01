package com.lxt.ms.workflow.graph.entity;

import com.lxt.ms.workflow.graph.utils.NodeBuilder;
import com.lxt.ms.workflow.graph.utils.TaskBuilder;

import java.util.HashMap;
import java.util.Map;

public class Task extends Vertex {
	private String nodeType = "task";
	private String form;
	private String formText;
	private String remindRuleId;
	private String remindRuleText;
	private int assignerType;
	private String assignerValue;
	private String handlerText;
	private int isTakeback;
	private int isDelegate;
	private int isSendback;
	private String sendbackWay;
	private String sendbackTargetId;
	private String sendbackTargetText;
	private int isCheck;
	private String checkTargetId;
	private String checkTargetText;
	private int isUpload;
	private int isAdvice;
	private String memo;
	private String href;

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getForm() {
		return form;
	}
	
	public void setForm(String form) {
		this.form = form;
	}

	public String getFormText() {
		return formText;
	}

	public void setFormText(String formText) {
		this.formText = formText;
	}

	public String getRemindRuleId() {
		return remindRuleId;
	}

	public void setRemindRuleId(String remindRuleId) {
		this.remindRuleId = remindRuleId;
	}

	public String getRemindRuleText() {
		return remindRuleText;
	}

	public void setRemindRuleText(String remindRuleText) {
		this.remindRuleText = remindRuleText;
	}

	public int getAssignerType() {
		return assignerType;
	}

	public void setAssignerType(int assignerType) {
		this.assignerType = assignerType;
	}

	public String getAssignerValue() {
		return assignerValue;
	}

	public void setAssignerValue(String assignerValue) {
		this.assignerValue = assignerValue;
	}

	public String getHandlerText() {
		return handlerText;
	}

	public void setHandlerText(String handlerText) {
		this.handlerText = handlerText;
	}

	public int getIsTakeback() {
		return isTakeback;
	}

	public void setIsTakeback(int isTakeback) {
		this.isTakeback = isTakeback;
	}

	public int getIsDelegate() {
		return isDelegate;
	}

	public void setIsDelegate(int isDelegate) {
		this.isDelegate = isDelegate;
	}

	public int getIsSendback() {
		return isSendback;
	}

	public void setIsSendback(int isSendback) {
		this.isSendback = isSendback;
	}

	public String getSendbackWay() {
		return sendbackWay;
	}
	
	public void setSendbackWay(String sendbackWay) {
		this.sendbackWay = sendbackWay;
	}
	
	public String getSendbackTargetId() {
		return sendbackTargetId;
	}

	public void setSendbackTargetId(String sendbackTargetId) {
		this.sendbackTargetId = sendbackTargetId;
	}

	public String getSendbackTargetText() {
		return sendbackTargetText;
	}

	public void setSendbackTargetText(String sendbackTargetText) {
		this.sendbackTargetText = sendbackTargetText;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public String getCheckTargetId() {
		return checkTargetId;
	}

	public void setCheckTargetId(String checkTargetId) {
		this.checkTargetId = checkTargetId;
	}

	public String getCheckTargetText() {
		return checkTargetText;
	}

	public void setCheckTargetText(String checkTargetText) {
		this.checkTargetText = checkTargetText;
	}

	public int getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(int isUpload) {
		this.isUpload = isUpload;
	}

	public int getIsAdvice() {
		return isAdvice;
	}

	public void setIsAdvice(int isAdvice) {
		this.isAdvice = isAdvice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Map<String, Object> getNodeAttributes() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("form", form);
		map.put("assignerType", assignerType);
		map.put("assignerValue", assignerValue);
//		map.put("formText", formText);
//		map.put("remindRuleId", remindRuleId);
//		map.put("remindRuleText", remindRuleText);
//		map.put("handlerType", handlerType);
//		map.put("handlerId", handlerId);
//		map.put("handlerText", handlerText);
//		map.put("sendbackWay", sendbackWay);
//		map.put("sendbackTargetId", sendbackTargetId);
//		map.put("sendbackTargetText", sendbackTargetText);
//		map.put("checkTargetId", checkTargetId);
//		map.put("checkTargetText", checkTargetText);
//		map.put("isTakeback", isTakeback);
//		map.put("isDelegate", isDelegate);
//		map.put("isSendback", isSendback);
//		map.put("isCheck", isCheck);
//		map.put("isUpload", isUpload);
//		map.put("isAdvice", isAdvice);
		return map;
	}

	@Override
	public NodeBuilder getBuilder() {
		return new TaskBuilder();
	}
}
