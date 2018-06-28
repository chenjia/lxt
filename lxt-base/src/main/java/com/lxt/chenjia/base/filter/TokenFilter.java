package com.lxt.chenjia.base.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lxt.chenjia.base.bean.web.Packages;
import com.lxt.chenjia.base.bean.web.ResponseWrapper;
import com.lxt.chenjia.base.utils.JSONUtils;
import com.lxt.chenjia.base.utils.JWTUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/api/*",filterName = "tokenFilter")
public class TokenFilter extends OncePerRequestFilter {
	@Value("${safeApi}")
	private String safeApi;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Set<String> safeApis= new HashSet<String>(Arrays.asList(safeApi.split(",")));
		
		if("/api/upload".equals(request.getRequestURI())){
			filterChain.doFilter(request, response);
			return;
		}
		
		String text = request.getParameter("request");
		Packages pkg = decryptRequest(text);
		
		if(pkg.getHead().getStatus() == 200){
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
			}else if(safeApis.contains(request.getRequestURI())){
				filterChain.doFilter(request, response);
				return;
			}else{
				pkg.getHead().setStatus(500);
				pkg.getHead().setMsg("未知的用户！");
			}
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(new ResponseWrapper(pkg).toJson());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	private Packages decryptRequest(String decryptedText) {
		Packages request = null;

		try {
			request = JSONUtils.json2Obj(SecurityUtils.decrypt(decryptedText), Packages.class);
		} catch (Exception e) {
			e.printStackTrace();
			request = new Packages();
			request.getHead().setStatus(500);
			request.getHead().setMsg("报文格式转化异常");
		}

		return request;
	}
}