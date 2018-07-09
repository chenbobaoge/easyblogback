package com.bobochen.easyblogback.eurekadatatemplate.Controller;


import com.bobochen.easyblogback.eurekadatatemplate.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MyTestController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name ;
    }



    @RequestMapping("/hi2")
    public String home2(@RequestParam String name) {

        redisService.set("key1",name);
        return redisService.get("key1");
    }
}
