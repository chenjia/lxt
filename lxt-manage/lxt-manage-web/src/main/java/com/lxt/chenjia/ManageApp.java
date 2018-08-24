package com.lxt.chenjia;

import java.util.HashMap;
import java.util.Map;

import org.directwebremoting.spring.DwrSpringServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lxt.chenjia.manage.mapper")
@ImportResource("classpath:dwr-spring-config.xml")
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
	
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        DwrSpringServlet servlet = new DwrSpringServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/chatEngine/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        
        initParameters.put("accessLogLevel","runtimeexception");
        initParameters.put("activeReverseAjaxEnabled","true");
        initParameters.put("allowScriptTagRemoting","true");
        initParameters.put("debug", "true");
        initParameters.put("initApplicationScopeCreatorsAtStartup","true");
        initParameters.put("jsonRpcEnabled","true");
        initParameters.put("jsonpEnabled","true");
        initParameters.put("maxWaitAfterWrite","500");
        initParameters.put("overridePath","/lxt-gateway/lxt-manage/chatEngine");
        initParameters.put("preferDataUrlSchema","false");
        initParameters.put("scriptSessionTimeout","1800000");
        
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
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
