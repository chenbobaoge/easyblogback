package com.bobochen.easyblogback.eurekaclientjdbc;

import com.bobochen.easyblogback.eurekaclientjdbc.redis.A;
import com.bobochen.easyblogback.eurekaclientjdbc.redis.JedisClient;
import com.bobochen.easyblogback.eurekaclientjdbc.redis.JedisClientPool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Autowired
    private A a;

    @Autowired
    @Qualifier("Pool")
    private JedisClient jedisClient;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name ;
    }


    @RequestMapping("/setgetredis")
    public String setgetredis(@RequestParam String name) {

        jedisClient.set("jedis1",name);
        return jedisClient.get("jedis1");

    }

}


