package com.lxt.chenjia.manage.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lxt.chenjia.common.bean.web.Packages;
import com.lxt.chenjia.common.bean.web.ResponseWrapper;
import com.lxt.chenjia.common.utils.JSONUtils;
import com.lxt.chenjia.common.utils.SpringUtils;
import com.lxt.chenjia.common.utils.UUIDUtils;

@RefreshScope
@Controller
public class ServiceController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${file.uploadPath}")
	private String uploadPath;
	
	@Value("${safeApi}")
	private String safeApi;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		System.out.println("\n[request service]" + request.getRequestURI() + "\n");
		
		logger.debug("Logger Level ：DEBUG");
	    logger.info("Logger Level ：INFO");
	    logger.error("Logger Level ：ERROR");

		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/{service}/{method}", method = RequestMethod.POST)
	public Packages api(HttpServletRequest request, HttpServletResponse httpServletResponse, @PathVariable String service, @PathVariable String method) throws Exception {
		System.out.println("\n[service api]" + service + "/" + method);
		
		String encryptedText = request.getParameter("request");
		System.out.println(encryptedText + "\n");
		Packages pkg = JSONUtils.json2Obj(encryptedText, Packages.class);

		try {
			if (pkg.getHead().getStatus() == 200) {
				pkg = callService(service, method, pkg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n[response service]" + service + "/" + method);
		System.out.println(JSONUtils.obj2Json(pkg) + "\n");
		return pkg;
	}

	@ResponseBody
	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	public ResponseWrapper upload(@RequestParam("file") MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		File dest = new File(uploadPath + "/" + UUIDUtils.UUID()+fileName.substring(fileName.lastIndexOf(".")));
		if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // 保存文件
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
}