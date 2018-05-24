package com.lxt.chenjia.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lxt.chenjia.base.bean.web.ServiceBean;

@Component
public class SpringUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private static Map<String, ServiceBean> beanMap = new HashMap<String, ServiceBean>();

	static {
		beanMap.put("login", new ServiceBean("loginService", "login"));
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		applicationContext = ac;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Map<String, ServiceBean> getBeanMap(){
		return beanMap;
	}

	
}
