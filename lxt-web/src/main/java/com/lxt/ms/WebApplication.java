package com.lxt.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class WebApplication {
    public static void main( String[] args ) {
        SpringApplication.run(WebApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean registerCasSingleSignOutFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("crossDomainFilter");
        registration.addUrlPatterns("/*");
        registration.setFilter(crossDomainFilter());
        registration.setOrder(10010);
        return registration;
    }

    @Bean(name = "crossDomainFilter")
    public Filter crossDomainFilter() {
        return new CrossDomainFilter();
    }

}
