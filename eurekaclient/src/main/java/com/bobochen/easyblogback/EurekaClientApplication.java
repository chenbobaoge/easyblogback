package com.bobochen.easyblogback;


import com.bobochen.easyblogback.dao.EasyblogDao;
import com.bobochen.easyblogback.entity.Easyblog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class, args);

    }

    @Autowired
    private EasyblogDao easyblogdao ;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name ;
    }


    @RequestMapping("/hi2/{name2}")
    public String home3(@PathVariable String name2,@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name2 ;
    }

    @RequestMapping(value = "/hi3/{name2}",method = RequestMethod.POST)
    public Easyblog home4(@PathVariable String name2, @RequestParam String name, @RequestBody  User user) {
        Easyblog m=  new Easyblog(0L,"ssss",22,new Date(),new BigDecimal("3.33"));

        easyblogdao.save(m);
        System.out.println("422222");
         System.out.println(name2+" "+name);
         user.setAccount("ssss");
         return m;
    }

    @RequestMapping(value = "/findByUserName",method = RequestMethod.GET)
    public List<Easyblog> home5() {
       List<Easyblog> bs= easyblogdao.findByUserName("ssss");
        return bs;
    }

    @RequestMapping(value = "/findByUserName2",method = RequestMethod.GET)
    public List<Easyblog> home6() {
        List<Easyblog> bs= easyblogdao.findByUserName2("2");
        return bs;
    }

    @RequestMapping(value = "/findByUserName3",method = RequestMethod.GET)
    public List<Easyblog> home7() {
        List<Easyblog> bs= easyblogdao.searchUserName("3");
        return bs;
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

