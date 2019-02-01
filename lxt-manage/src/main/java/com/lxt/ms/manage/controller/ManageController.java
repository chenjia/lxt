package com.lxt.ms.manage.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxt.ms.common.bean.web.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.ResponseWrapper;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.SpringUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RefreshScope
@Controller
public class ManageController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${file.uploadPath}")
	private String uploadPath;
	
	@Value("${safeApi}")
	private String safeApi;

	@ResponseBody
	@RequestMapping(value = "/{service}/{method}", method = RequestMethod.POST)
	public Packages api(HttpServletRequest request, HttpServletResponse httpServletResponse, @PathVariable String service, @PathVariable String method) throws Exception {
		System.out.println("【service api】" + service + "/" + method);

		String requestJson = request.getParameter("request");
		System.out.println(requestJson + "\n");
		Packages pkg = JSONUtils.json2Obj(requestJson, Packages.class);

		try {
			if (pkg.getHead().getStatus() == 200) {
				pkg = callService(service, method, pkg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pkg.getHead().setStatus(500);
			pkg.getHead().setMsg("服务调用异常");
		}

		System.out.println("\n[response service]" + service + "/" + method);
		System.out.println(JSONUtils.obj2Json(pkg) + "\n");
		return pkg;
	}

	@SuppressWarnings("unchecked")
	private Packages callService(String service, String method, Packages pkg) {
		Packages result = pkg;

		try {
			ApplicationContext ac = SpringUtils.getApplicationContext();
			Object serviceObj = ac.getBean(service + "Service");
			Class clazz =  serviceObj.getClass();
			Method[] methods = clazz.getMethods();
			Method methodObj = null;
			for (Method m : methods) {
				if (m.getName().equals(method)) {
					methodObj = m;
					break;
				}
			}
			LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
			String[] paramNames = u.getParameterNames(methodObj);
			Object[] args = new Object[paramNames.length];
			Map<String, Object> map = (Map<String, Object>) pkg.getBody().getData();
			for (int i = 0; i < paramNames.length; i++) {
				args[i] = map.get(paramNames[i]);
			}
			result = (Packages) methodObj.invoke(serviceObj, args);
		} catch (Exception e) {
			e.printStackTrace();
			result.getHead().setStatus(500);
			int msgLength = e.getMessage().length() > 100 ? 100 : e
					.getMessage().length();
			result.getHead().setMsg(
					"接口调用异常:" + e.getMessage().substring(0, msgLength));
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/testconfig")
	public Packages testconfig() throws Exception {
		System.out.println("【test config】" + uploadPath);
		Packages pkg = new Packages();
		pkg.getBody().setData(uploadPath);

		return pkg;
	}

	@RequestMapping(value = "/pay")
	public ModelAndView pay(HttpServletRequest request, RedirectAttributes attrs) throws Exception {
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("money"));
		ModelAndView model = new ModelAndView();
		attrs.addAttribute("name", request.getParameter("name"));

		attrs.addAttribute("money", request.getParameter("money"));
		model.setViewName("redirect:/ppp");
		return model;
	}

	@RequestMapping(value = "/ppp")
	public ModelAndView ppp(@ModelAttribute("name") String name, @ModelAttribute("money") String money) throws Exception {
		System.out.println("name:"+name);
		System.out.println("money:"+money);
		ModelAndView model = new ModelAndView();
		model.setViewName("test");
		return model;
	}
}