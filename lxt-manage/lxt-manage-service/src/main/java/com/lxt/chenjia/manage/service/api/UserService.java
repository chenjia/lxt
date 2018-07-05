package com.lxt.chenjia.manage.service.api;

import com.lxt.chenjia.common.bean.web.Packages;
import com.lxt.chenjia.common.exception.APIException;

public interface UserService{
	
	public abstract Packages captcha() throws APIException;
	
	public abstract Packages login(String username, String password, String captcha, String captchaToken) throws APIException;
	
}
