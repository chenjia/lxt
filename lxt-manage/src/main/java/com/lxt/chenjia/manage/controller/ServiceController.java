package com.lxt.chenjia.manage.controller;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxt.chenjia.base.bean.web.Packages;
import com.lxt.chenjia.base.bean.web.ResponseWrapper;
import com.lxt.chenjia.base.utils.JsonUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;
import com.lxt.chenjia.base.utils.SpringUtils;
import com.lxt.chenjia.manage.service.api.ApiService;

@Controller
public class ServiceController {

	@RequestMapping("/")
	public String index() {
		return "/index";
	}

	@ResponseBody
	@RequestMapping(value = "/api/{serviceName}", method = RequestMethod.POST)
	public ResponseWrapper api(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String serviceName, @RequestParam String request) throws Exception {
		System.out.println("【call api:】" + serviceName);

		Packages pkg = decryptRequest(request);

		if (pkg.getHead().getStatus() == 200) {
			String servicePath = ApiService.getServicePath(serviceName);

			if (servicePath == null) {
				pkg.getHead().setStatus(500);
				pkg.getHead().setMsg("未知的接口服务");
			} else {
				pkg = callService(servicePath, pkg);
			}
		}

		return new ResponseWrapper(pkg);
	}

	private Packages decryptRequest(String decryptedText) {
		Packages request = null;

		try {
			request = JsonUtils.json2Obj(SecurityUtils.decrypt(decryptedText), Packages.class);
		} catch (Exception e) {
			e.printStackTrace();
			request = new Packages();
			request.getHead().setStatus(500);
			request.getHead().setMsg("报文格式转化异常");
		}

		return request;
	}
	
	@SuppressWarnings("unchecked")
	private Packages callService(String servicePath, Packages pkg){
		Packages result = null;
		
		try {
			String[] serviceArray = servicePath.split("[.]");
			ApplicationContext ac = SpringUtils.getApplicationContext();
			Object service = ac.getBean(serviceArray[0]);
			Class<ApiService> clazz = (Class<ApiService>) service.getClass();
			Method[] methods = clazz.getMethods();
			Method method = null;
			for (Method m : methods) {
				if (m.getName().equals(serviceArray[1])) {
					method = m;
					break;
				}
			}
			LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
			String[] paramNames = u.getParameterNames(method);
			Object[] args = new Object[paramNames.length];
			Map<String, Object> map = (Map<String, Object>) pkg.getBody().getData();
			for (int i = 0; i < paramNames.length; i++) {
				args[i] = map.get(paramNames[i]);
			}
			
			result = (Packages) method.invoke(service, args);
		} catch (Exception e) {
			e.printStackTrace();
			pkg.getHead().setStatus(500);
			int msgLength = e.getMessage().length()>100?100:e.getMessage().length();
			pkg.getHead().setMsg("接口调用异常:"+e.getMessage().substring(0,msgLength));
		}
		
		return result;
	}
}