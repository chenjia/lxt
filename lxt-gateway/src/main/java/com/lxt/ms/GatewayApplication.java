package com.lxt.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lxt.ms.gateway.filter.RequestFilter;
import com.lxt.ms.gateway.filter.ResponseFilter;


@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
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
