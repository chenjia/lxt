package com.lxt.chenjia.manage.service.api;

import com.lxt.chenjia.base.bean.web.Packages;
import com.lxt.chenjia.base.exception.ServiceException;

public interface LoginService extends ApiService{
	
	public abstract Packages captcha() throws ServiceException;
	
	public abstract Packages login(String username, String password, String captcha, String captchaToken) throws ServiceException;
	
}
