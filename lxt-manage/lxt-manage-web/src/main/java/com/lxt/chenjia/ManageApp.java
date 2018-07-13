package com.lxt.chenjia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lxt.chenjia.manage.mapper")
@SpringBootApplication
public class ManageApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ManageApp.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ManageApp.class, args);
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
