package com.lxt.chenjia.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import com.lxt.chenjia.common.bean.web.Packages;
import com.lxt.chenjia.common.utils.JSONUtils;
import com.lxt.chenjia.common.utils.JWTUtils;
import com.lxt.chenjia.common.utils.SecurityUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

public class RequestFilter extends ZuulFilter{
	private final static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
	@Value("${safeDomain}")
	private String safeDomain;
	
	@Value("${safeApi}")
	private String safeApi;
	
	@Value("${ignoreUrl}")
    private String ignoreUrl;
	
	@Override
	public Object run() throws ZuulException{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		System.out.println("\n"+request.getContextPath()+"\n"+request.getRequestURI()+"\n");
		
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI().replaceAll(contextPath, "");

		if (!StringUtils.contains(ignoreUrl, uri)) {
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
					Map<String, Object> claimMap = new HashMap<String, Object>();
					claimMap.put("userId", userId);
					Map<String, Object> map = JWTUtils.parse(token);
					if (map != null) {
						return null;
					} else {
						pkg.getHead().setStatus(500);
						pkg.getHead().setMsg("token验证失败！");
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
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
        logger.info("\n[TokenFilter]" + req.getRequestURI() + "\n");
        return StringUtils.equalsIgnoreCase(req.getMethod(), "post") && !StringUtils.contains(safeApi, req.getRequestURI());
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