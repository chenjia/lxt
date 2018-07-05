package com.lxt.chenjia.manage.service.bo;

import com.lxt.chenjia.manage.model.User;
import com.lxt.chenjia.manage.model.UserSetting;

public class LoginBO {
	private User user;

	private UserSetting userSetting;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSetting getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(UserSetting userSetting) {
		this.userSetting = userSetting;
	}

}
