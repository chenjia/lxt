package com.lxt.ms;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.filter.TokenFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Filter;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.lxt.ms.manage.mapper")
@SpringBootApplication
public class ManageApplication{

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}
	
//	@Bean
//    public FilterRegistrationBean someFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(tokenFilter());
//        registration.addUrlPatterns("/api/*");
//        registration.setName("tokenFilter");
//        return registration;
//    }
//
//    @Bean(name = "tokenFilter")
//    public Filter tokenFilter() {
//        return new TokenFilter();
//    }
}
