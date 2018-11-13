package com.lxt.ms.chat.vo;

import java.util.ArrayList;
import java.util.List;

public class ChatFriendCategoryVO {
	private String friendCategoryId;

	private String userId;

	private String name;

	private Integer sortSeq;

	private List<ChatFriendVO> friends = new ArrayList<ChatFriendVO>();

	public String getFriendCategoryId() {
		return friendCategoryId;
	}

	public void setFriendCategoryId(String friendCategoryId) {
		this.friendCategoryId = friendCategoryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
