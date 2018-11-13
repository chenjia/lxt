package com.lxt.ms.manage.vo;

import java.util.List;

public class ChatGroupVO {
	private String groupId;

	private String userId;

	private String groupName;

	private Integer sortSeq;

	private List<ChatFriendVO> friends;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(Integer sortSeq) {
		this.sortSeq = sortSeq;
	}

	public List<ChatFriendVO> getFriends() {
		return friends;
	}

	public void setFriends(List<ChatFriendVO> friends) {
		this.friends = friends;
	}

}
