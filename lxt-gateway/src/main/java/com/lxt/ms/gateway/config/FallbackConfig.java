package com.lxt.ms.gateway.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.utils.JSONUtils;

@Component
public class FallbackConfig implements FallbackProvider {
	Logger logger = LoggerFactory.getLogger(FallbackConfig.class);

	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//		if (cause != null && cause.getCause() != null) {
//			System.out.println(cause.getMessage());
//			String reason = cause.getCause().getMessage();
//			System.out.println("\n[fallback]"+reason+"\n");
//		}

		System.out.println("【fallback msg】"+cause.getMessage());
		System.out.println("【fallback cause】"+cause.getCause().getMessage());

		
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				Packages pkg = new Packages();
				pkg.getHead().setStatus(500);
				pkg.getHead().setMsg("服务器正在开小差");
				return new ByteArrayInputStream(JSONUtils.obj2Json(pkg).replace("\r\n", "").replace("\n", "").getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}
			
			@Override
			public void close() {
				
			}
		};
	}

}
