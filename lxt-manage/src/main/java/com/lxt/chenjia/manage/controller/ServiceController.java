package com.lxt.chenjia.manage.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lxt.chenjia.base.bean.web.Packages;
import com.lxt.chenjia.base.bean.web.ResponseWrapper;
import com.lxt.chenjia.base.utils.JSONUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;
import com.lxt.chenjia.base.utils.SpringUtils;
import com.lxt.chenjia.base.utils.UUIDUtils;
import com.lxt.chenjia.manage.service.api.ApiService;

@Controller
public class ServiceController {
	@Value("${file.uploadPath}")
	private String uploadPath;

	@RequestMapping("/")
	public String index() {
		return "/index";
	}

	@ResponseBody
	@RequestMapping(value = "/api/{service}/{method}", method = RequestMethod.POST)
	public ResponseWrapper api(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@PathVariable String service, @PathVariable String method, @RequestParam String request)
			throws Exception {
		System.out.println("【call api:】" + service + "/" + method);

		Packages pkg = decryptRequest(request);

		if (pkg.getHead().getStatus() == 200) {
			pkg = callService(service, method, pkg);
		}

		return new ResponseWrapper(pkg);
	}

	@ResponseBody
	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	public ResponseWrapper upload(@RequestParam("file") MultipartFile file)
			throws Exception {
		// if(file.isEmpty()){
		// return "false";
		// }
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		File dest = new File(uploadPath + "/" + UUIDUtils.UUID()+fileName.substring(fileName.lastIndexOf(".")));
		if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // 保存文件
			// return "true";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "false";
		}

		return null;
	}

	private Packages decryptRequest(String decryptedText) {
		Packages request = null;

		try {
			request = JSONUtils.json2Obj(SecurityUtils.decrypt(decryptedText),
					Packages.class);
		} catch (Exception e) {
			e.printStackTrace();
			request = new Packages();
			request.getHead().setStatus(500);
			request.getHead().setMsg("报文格式转化异常");
		}

		return request;
	}

	@SuppressWarnings("unchecked")
	private Packages callService(String service, String method, Packages pkg) {
		Packages result = pkg;

		try {
			ApplicationContext ac = SpringUtils.getApplicationContext();
			Object serviceObj = ac.getBean(service + "Service");
			Class<ApiService> clazz = (Class<ApiService>) serviceObj.getClass();
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
			Map<String, Object> map = (Map<String, Object>) pkg.getBody()
					.getData();
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