package com.bobochen.easyblogback.allinone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AllInOneApplication {
    public static void main(String[] args) {

        SpringApplication.run(AllInOneApplication.class, args);

    }

}
