package com.bobochen.easyblogback.eurekadatatemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaDataTemplateApplication {
    public static void main(String[] args) {

        SpringApplication.run(EurekaDataTemplateApplication.class, args);

    }

}
