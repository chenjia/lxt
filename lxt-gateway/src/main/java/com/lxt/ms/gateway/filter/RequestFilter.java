package com.lxt.ms.gateway.filter;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.utils.CacheUtils;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.JWTUtils;
import com.lxt.ms.common.utils.SecurityUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class RequestFilter extends ZuulFilter{
	@Value("#{'${filterUrls.services}'.split(',')}")
	private String[] services;

	@Value("${filterUrls.apis}")
	private String apis;

	@Value("#{'${filterUrls.excludes}'.split(',')}")
	private String[] excludes;

	@Override
	public Object run() throws ZuulException{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		System.out.println("【contextPath】"+request.getContextPath());
		System.out.println("【requestURI】"+request.getRequestURI());
		
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI().replaceAll(contextPath, "");

		String encryptedText = request.getParameter("request");
		Packages pkg = new Packages();
		String decryptedText = null;
		try {
			decryptedText = SecurityUtils.decrypt(encryptedText);
			pkg = JSONUtils.json2Obj(decryptedText, Packages.class);
		} catch (Exception e) {
			e.printStackTrace();
			pkg.getHead().setStatus(500);
			pkg.getHead().setMsg("报文解密异常！");
		}

		if (pkg.getHead().getStatus() == 200 && apis.indexOf(uri) == -1) {
			String token = pkg.getHead().getToken();
			String userId = pkg.getHead().getUserId();

			if (StringUtils.isNotEmpty(userId)) {
				try {
					Map<String, Object> map = JWTUtils.parse(token);
					if(userId.equals(map.get("userId"))){
						Set<Object> resourceSet = CacheUtils.sGet("RESOURCE_"+userId);
						if(resourceSet == null || !resourceSet.contains(uri)){
							System.out.println("forbidden:"+uri);
						}
//						if(resourceSet == null || !resourceSet.contains(uri)){
//							pkg.getHead().setStatus(500);
//							pkg.getHead().setMsg("未授权的访问，请联系管理员！");
//						}
					}else {
						pkg.getHead().setStatus(500);
						pkg.getHead().setMsg("token验证失败！");
					}
				} catch (Exception e) {
					e.printStackTrace();
					pkg.getHead().setStatus(500);
					pkg.getHead().setMsg("token转换失败！");
				}
			}
		}

		InputStream in = (InputStream) ctx.get("requestEntity");
		if (in == null) {
			try {
				in = ctx.getRequest().getInputStream();
				String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
				body = "request=" + JSONUtils.obj2Json(pkg);

				final byte[] reqBodyBytes = body.getBytes();
				ctx.setRequest(new HttpServletRequestWrapper(ctx.getRequest()) {
					@Override
					public ServletInputStream getInputStream() throws IOException {
						return new ServletInputStreamWrapper(reqBodyBytes);
					}

					@Override
					public int getContentLength() {
						return reqBodyBytes.length;
					}

					@Override
					public long getContentLengthLong() {
						return reqBodyBytes.length;
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
				throw new ZuulException(e, 500, "获取输入流失败");
			}
		}

		return null;
	}

	@Override
	public boolean shouldFilter() {
		boolean shouldFilter = false;
		
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String uri = request.getRequestURI();
		for(String url : services){
			if(uri.startsWith(url)){
				shouldFilter = true;
				break;
			}
		}

		for(String exclude : excludes){
			if(uri.startsWith(exclude)){
				shouldFilter = false;
				break;
			}
		}
		
		return shouldFilter;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return "pre";
	}
}