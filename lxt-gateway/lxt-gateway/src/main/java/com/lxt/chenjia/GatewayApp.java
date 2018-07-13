package com.lxt.chenjia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lxt.chenjia.gateway.filter.RequestFilter;
import com.lxt.chenjia.gateway.filter.ResponseFilter;


@EnableZuulProxy
@SpringBootApplication
public class GatewayApp extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GatewayApp.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class, args);
	}
	
	@Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }
	
	@Bean
    public ResponseFilter responseFilter() {
        return new ResponseFilter();
    }
}
