package com.lxt.chenjia.service.api;

import java.util.List;

import com.lxt.chenjia.base.exception.ServiceException;

public interface LoginService {
	
	public abstract List<Object> login(String username, String password) throws ServiceException;
}
