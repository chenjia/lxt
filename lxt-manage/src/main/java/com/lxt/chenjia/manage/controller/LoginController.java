package com.lxt.chenjia.manage.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxt.chenjia.base.bean.web.RequestWrapper;
import com.lxt.chenjia.base.bean.web.Response;
import com.lxt.chenjia.base.bean.web.ResponseWrapper;

@RestController
public class LoginController {
	
	@RequestMapping("/login")
	public ResponseWrapper login(@RequestAttribute RequestWrapper requestWrapper) {
		Map<String, Object> map = requestWrapper.data();
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		Response response = new Response();
		if("admin".equals(username) && "admin".equals(password)){
			response.getBody().getData().put("msg", "登录成功");
		}
		
		return new ResponseWrapper(response);
	}
	
	@RequestMapping("/logout")
	public String logout() {
		System.out.println("【call logout:】");
		return "logout";
	}
}
