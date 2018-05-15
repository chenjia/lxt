package com.lxt.chenjia.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxt.chenjia.base.bean.web.Request;
import com.lxt.chenjia.base.bean.web.RequestWrapper;
import com.lxt.chenjia.base.utils.FormatUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;

@Controller
public class ServiceController {
	
	@RequestMapping("/")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/callService/{serviceId}")
	public String callService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String serviceId, @RequestParam String params) throws Exception {
		System.out.println("【call service:】"+serviceId);
		RequestWrapper requestWrapper = new RequestWrapper();
		Request request = null;
		try {
			request = FormatUtils.json2Obj(SecurityUtils.decrypt(params), Request.class);
			requestWrapper.setRequest(request);
		} catch (Exception e) {
			request = new Request();
			request.getHead().setStatus(500);
			request.getBody().getData().put("msg", "报文格式转化异常");
			requestWrapper.setRequest(request);
			
			httpServletResponse.setContentType("application/json;charset=utf-8");
			httpServletResponse.getWriter().write(SecurityUtils.encrypt(FormatUtils.obj2Json(requestWrapper)));
			httpServletResponse.getWriter().flush();
			httpServletResponse.getWriter().close();
			return null;
		}
		
		httpServletRequest.setAttribute("requestWrapper", new RequestWrapper(request));
		return "forward:/" + serviceId;
	}

}