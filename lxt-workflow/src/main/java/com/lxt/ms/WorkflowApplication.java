package com.lxt.ms;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCaching
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.lxt.ms.workflow.mapper")
@SpringBootApplication(scanBasePackages = "com.lxt.ms.workflow", exclude = SecurityAutoConfiguration.class)
public class WorkflowApplication {
    public static void main( String[] args ){
        SpringApplication.run(WorkflowApplication.class, args);
    }
}
