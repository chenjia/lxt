package com.lxt.chenjia.gateway.filter;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import com.lxt.chenjia.common.utils.SecurityUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ResponseFilter extends ZuulFilter {
private final static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	@Value("${safeDomain}")
	private String safeDomain;
	
	@Value("${safeApi}")
	private String safeApi;
	
	@Value("${ignoreUrl}")
    private String ignoreUrl;
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		
		System.out.println("\n"+request.getContextPath()+"\n"+request.getRequestURI()+"\n");
		
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
		
		try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            String encryptedText = SecurityUtils.encrypt(body);
            ctx.setResponseBody("{\"response\":\""+ encryptedText.replace("\r\n", "").replace("\n", "") +"\"}");
        } catch (Exception e) {
            throw new ZuulException(e, 500, "报文加密异常");
        }
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
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
