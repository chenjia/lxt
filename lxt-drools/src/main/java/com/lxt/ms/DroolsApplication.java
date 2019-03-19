package com.lxt.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lxt.ms.drools.mapper")
@SpringBootApplication
public class DroolsApplication {
    public static void main(String[] args){
        SpringApplication.run(DroolsApplication.class, args);
    }
}
