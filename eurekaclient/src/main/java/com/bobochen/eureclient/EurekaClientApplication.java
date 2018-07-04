package com.bobochen.eureclient;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class, args);

    }


    @RequestMapping("/hi")
    public String home(@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name ;
    }


    @RequestMapping("/hi2/{name2}")
    public String home3(@PathVariable String name2,@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name2 ;
    }

    @RequestMapping(value = "/hi3/{name2}",method = RequestMethod.POST)
    public User home4(@PathVariable String name2,@RequestParam String name,@RequestBody  User user) {

         System.out.println(name2+" "+name);
         user.setAccount("ssss");
         return user;
    }
}
@Component
@WebFilter(urlPatterns = "/*",filterName = "MyFilter")
class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

