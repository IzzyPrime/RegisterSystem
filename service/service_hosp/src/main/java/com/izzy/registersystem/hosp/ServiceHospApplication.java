package com.izzy.registersystem.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Izzy
 * @date 2021/4/2
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.izzy")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
