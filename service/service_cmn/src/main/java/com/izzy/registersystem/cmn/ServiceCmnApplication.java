package com.izzy.registersystem.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Izzy
 * @date 2021/4/5
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.izzy")
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
