package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.model.ProcessExample;
import com.lxt.ms.manage.model.User;
import com.lxt.ms.manage.model.UserExample;

public interface UserService{
	
	public abstract Packages captcha() throws APIException;
	
	public abstract Packages login(String username, String password, String captcha, String captchaToken) throws APIException;

	public abstract Packages list(UserExample example, PageData pageData) throws APIException;

	public abstract Packages save(User user) throws APIException;

	public abstract Packages delete(String userId) throws APIException;

	public abstract Packages status(String userId, int status) throws APIException;

	public abstract Packages grant(String userId, String[] roleIds) throws APIException;
}
