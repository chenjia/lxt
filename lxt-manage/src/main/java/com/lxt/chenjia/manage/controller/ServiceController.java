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

import com.lxt.chenjia.base.bean.web.Request;
import com.lxt.chenjia.base.bean.web.RequestWrapper;
import com.lxt.chenjia.base.bean.web.Response;
import com.lxt.chenjia.base.bean.web.ResponseWrapper;
import com.lxt.chenjia.base.bean.web.ServiceBean;
import com.lxt.chenjia.base.utils.FormatUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;
import com.lxt.chenjia.base.utils.SpringUtils;

@Controller
public class ServiceController {
	
	@RequestMapping("/")
	public String index() {
		return "/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/{serviceId}", method=RequestMethod.POST)
	public ResponseWrapper callService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String serviceId, @RequestParam String request) throws Exception {
		System.out.println("【call api:】"+serviceId);
		RequestWrapper requestWrapper = new RequestWrapper();
		Request req = null;
		try {
			req = FormatUtils.json2Obj(SecurityUtils.decrypt(request), Request.class);
			requestWrapper.setRequest(req);
		} catch (Exception e) {
			req = new Request();
			req.getHead().setStatus(500);
			req.getHead().setMsg("报文格式转化异常");
			requestWrapper.setRequest(req);
			
			httpServletResponse.setContentType("application/json;charset=utf-8");
			httpServletResponse.getWriter().write(SecurityUtils.encrypt(FormatUtils.obj2Json(requestWrapper)));
			httpServletResponse.getWriter().flush();
			httpServletResponse.getWriter().close();
			return null;
		}
		
		ServiceBean serviceBean = SpringUtils.getBeanMap().get(serviceId);
		ApplicationContext ac = SpringUtils.getApplicationContext();
		Object service = ac.getBean(serviceBean.getName());
		Class clazz = service.getClass();
		Method[] methods = clazz.getMethods();
		Method method = null;
		for(Method m : methods){
			if(m.getName().equals(serviceBean.getMethod())){
				method = m;
				break;
			}
		}
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		Object[] args = new Object[paramNames.length];
		Object data = req.getBody().getData();
		Map<String, Object> map = (Map<String, Object>) req.getBody().getData();
		for (int i = 0; i < paramNames.length; i++) {
			args[i] = map.get(paramNames[i]);
		}
		Object result = method.invoke(service, args);
		
		Response response = new Response();
		response.getBody().setData(result);
		
		return new ResponseWrapper(response);
	}
	
}