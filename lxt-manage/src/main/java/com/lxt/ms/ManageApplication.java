package com.lxt.ms;

import java.util.HashMap;
import java.util.Map;

import org.directwebremoting.spring.DwrSpringServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.lxt.ms.manage.mapper")
@SpringBootApplication
public class ManageApplication{

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
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
