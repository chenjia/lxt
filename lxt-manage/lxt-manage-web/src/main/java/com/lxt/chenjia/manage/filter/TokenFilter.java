package com.lxt.chenjia.manage.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lxt.chenjia.common.bean.web.Packages;
import com.lxt.chenjia.common.utils.JSONUtils;
import com.lxt.chenjia.common.utils.JWTUtils;
import com.lxt.chenjia.common.utils.SecurityUtils;

@Component
public class TokenFilter implements Filter {
	@Value("${server.servlet.contextPath}")
	private String contextPath;
	
	@Value("${safeDomain}")
	private String safeDomain;
	
	@Value("${safeApi}")
	private String safeApi;
	
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		Set<String> safeApis = new HashSet<String>(Arrays.asList(safeApi
				.split(",")));
		Set<String> allowedOrigins = new HashSet<String>(
				Arrays.asList(safeDomain.split(",")));
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

		if (!"/api/upload".equals(request.getRequestURI())) {
			String text = request.getParameter("request");
			Packages pkg = decryptRequest(text);

			if (pkg.getHead().getStatus() == 200) {
				String token = pkg.getHead().getToken();
				String userId = pkg.getHead().getUserId();

				if (!StringUtils.isEmpty(userId)) {
					Map<String, Object> claimMap = new HashMap<String, Object>();
					claimMap.put("userId", userId);
					Map<String, Object> map = JWTUtils.parse(token);
					if (map != null) {
						filterChain.doFilter(request, response);
						return;
					} else {
						pkg.getHead().setStatus(500);
						pkg.getHead().setMsg("token验证失败！");
					}
				} else if (!safeApis.contains(request.getRequestURI()
						.replaceAll(contextPath, ""))) {
					pkg.getHead().setStatus(500);
					pkg.getHead().setMsg("未知的用户！");
				}
			}

			request.setAttribute("pkg", pkg);
		}

		filterChain.doFilter(request, response);
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

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}