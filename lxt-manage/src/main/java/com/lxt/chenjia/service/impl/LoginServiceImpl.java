package com.lxt.chenjia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lxt.chenjia.base.exception.ServiceException;
import com.lxt.chenjia.service.api.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Override
	public List<Object> login(String username, String password)
			throws ServiceException {
		List<Object> list = new ArrayList<Object>();
		list.add("hello");
		list.add("world");
		
		return list;
	}
}
