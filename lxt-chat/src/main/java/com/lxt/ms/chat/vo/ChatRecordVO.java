package com.lxt.ms.chat.vo;


import com.lxt.ms.chat.model.ChatRecord;

public class ChatRecordVO extends ChatRecord {
	private String sendUser;
	private String receiveUser;
	private long insertTimeMillis;
	
	public String getSendUser() {
		return sendUser;
	}
	
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public long getInsertTimeMillis() {
		return super.getInsertTime().getTime();
	}
	
	public void setInsertTimeMillis(long insertTimeMillis) {
		this.insertTimeMillis = insertTimeMillis;
	}
	
}
