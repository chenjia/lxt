package com.lxt.ms.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.JWTUtils;
import com.lxt.ms.common.utils.SecurityUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

@SuppressWarnings("unchecked")
public class RequestFilter extends ZuulFilter{
	@Value("${filterUrl}")
	private String filterUrl;
	
	@Value("${safeDomain}")
	private String safeDomain;
	
	@Value("${safeApi}")
	private String safeApi;
	
	@Value("${unencryptedApi}")
    private String unencryptedApi;
	
	@Override
	public Object run() throws ZuulException{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		System.out.println("【contextPath】"+request.getContextPath());
		System.out.println("【requestURI】"+request.getRequestURI());
		
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI().replaceAll(contextPath, "");

		if (!StringUtils.contains(unencryptedApi, uri)) {
			String encryptedText = request.getParameter("request");
			Packages pkg = null;
			String decryptedText = null;
			try {
				decryptedText = SecurityUtils.decrypt(encryptedText);
				pkg = JSONUtils.json2Obj(decryptedText, Packages.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ZuulException(e, 500, "报文解密异常");
			}

			if (pkg.getHead().getStatus() == 200) {
				String token = pkg.getHead().getToken();
				String userId = pkg.getHead().getUserId();

				if (!StringUtils.isEmpty(userId)) {
					try {
						Map<String, Object> map = JWTUtils.parse(token);
						if(!userId.equals(map.get("userId"))){
							pkg.getHead().setStatus(500);
							pkg.getHead().setMsg("token验证失败！");
							ctx.setSendZuulResponse(false);
						}
					} catch (Exception e) {
						e.printStackTrace();
						pkg.getHead().setStatus(500);
						pkg.getHead().setMsg("token转换失败！");
						ctx.setSendZuulResponse(false);
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
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		boolean shouldFilter = false;
		
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI().replaceAll(contextPath, "");
		
		String[] filterUrls = filterUrl.split(",");
		for(String url : filterUrls){
			if(uri.startsWith(url)){
				shouldFilter = true;
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