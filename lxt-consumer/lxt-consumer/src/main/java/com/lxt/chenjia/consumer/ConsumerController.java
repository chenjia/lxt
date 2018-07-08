package com.lxt.chenjia.consumer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.lxt.chenjia.common.bean.web.Packages;
import com.lxt.chenjia.common.bean.web.ResponseWrapper;

@Controller
public class ConsumerController {
	
	@Value("${safeDomain}")
	private String safeDomain;
	
	@Value("${safeApi}")
	private String safeApi;
	
	@Autowired
    RestTemplate restTemplate;
	
	@ResponseBody
	@RequestMapping(value = "/api/{service}/{method}", method = RequestMethod.POST)
	public ResponseWrapper api(HttpServletRequest request, HttpServletResponse response, @PathVariable String service, @PathVariable String method) throws Exception {
		System.out.println("\n[Consumer api]" + service + "/" + method + "\n");
		
		Set<String> safeApis = new HashSet<String>(Arrays.asList(safeApi.split(",")));
		Set<String> allowedOrigins = new HashSet<String>(Arrays.asList(safeDomain.split(",")));
		String originHeader = request.getHeader("Origin");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		if (allowedOrigins.contains(originHeader)) {
			response.setHeader("Access-Control-Allow-Origin", originHeader);
			response.setHeader("Access-Control-Allow-Methods",
					"POST,GET,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin,X-Requested-With,Content-Type,Accept,token");
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}
		
		
		
		
		
		
		
		
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("request", request.getParameter("request"));
		
		Packages pkg = null;
		try {
			pkg = restTemplate.postForObject("http://lxt-manage/lxt-manage/api/" + service +"/"+ method, paramMap, Packages.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseWrapper(pkg);
	}
}