package com.lxt.ms.gateway.filter;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import com.lxt.ms.common.utils.SecurityUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ResponseFilter extends ZuulFilter {
	@Value("#{'${filterUrls.services}'.split(',')}")
	private String[] services;

	@Value("#{'${filterUrls.origins}'.split(',')}")
	private Set<String> origins;

	@Value("#{'${filterUrls.excludes}'.split(',')}")
    private String[] excludes;

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		String origin = request.getHeader("Origin");
		if (origins.contains(origin)) {
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Methods",
					"POST,GET,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin,X-Requested-With,Content-Type,Accept,token");
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}else {
			System.out.println("【origin】"+origin);
		}
		
		try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            String encryptedText = SecurityUtils.encrypt(body);
            ctx.setResponseBody("{\"response\":\""+ encryptedText.replaceAll("\r\n|\n", "") +"\"}");
        } catch (Exception e) {
            throw new ZuulException(e, 500, "报文加密异常");
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
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return "post";
	}
}
