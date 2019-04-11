package com.lxt.ms;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;
import java.util.Map;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.lxt.ms.push.mapper")
@ImportResource("classpath:dwr-spring-config.xml")
@SpringBootApplication
public class PushApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[] { PushApplication.class });
    }

    public static void main(String[] args) {
        SpringApplication.run(PushApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean pushServletRegistrationBean() {
        DwrSpringServlet servlet = new DwrSpringServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/pushEngine/*");
        Map<String, String> initParameters = new HashMap<String, String>();

        initParameters.put("accessLogLevel","runtimeexception");
        initParameters.put("activeReverseAjaxEnabled","true");
        initParameters.put("allowScriptTagRemoting","true");
        initParameters.put("debug", "true");
        initParameters.put("initApplicationScopeCreatorsAtStartup","true");
        initParameters.put("jsonRpcEnabled","true");
        initParameters.put("jsonpEnabled","true");
        initParameters.put("maxWaitAfterWrite","500");
        initParameters.put("overridePath","/api/push/pushEngine");
        initParameters.put("preferDataUrlSchema","false");
        initParameters.put("scriptSessionTimeout","1800000");

        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }
}
