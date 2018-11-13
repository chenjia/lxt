package com.lxt.ms.manage.vo;

public class ChatFriendVO {
	private String userId;

	private String friendId;

	private String friendCategoryId;

	private String memo;

	private Integer sortSeq;

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriendCategoryId() {
		return friendCategoryId;
	}

	public void setFriendCategoryId(String friendCategoryId) {
		this.friendCategoryId = friendCategoryId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(Integer sortSeq) {
		this.sortSeq = sortSeq;
	}

}
