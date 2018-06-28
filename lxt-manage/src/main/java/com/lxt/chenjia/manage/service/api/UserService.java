package com.lxt.chenjia.manage.service.api;

import com.lxt.chenjia.base.bean.web.Packages;
import com.lxt.chenjia.base.exception.APIException;

public interface UserService extends ApiService{
	
	public abstract Packages captcha() throws APIException;
	
	public abstract Packages login(String username, String password, String captcha, String captchaToken) throws APIException;
	
}
