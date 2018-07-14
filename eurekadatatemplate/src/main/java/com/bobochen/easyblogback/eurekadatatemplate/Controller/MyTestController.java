package com.bobochen.easyblogback.eurekadatatemplate.Controller;


import com.bobochen.easyblogback.eurekadatatemplate.dao.MyUserDao;
import com.bobochen.easyblogback.eurekadatatemplate.entitiy.MyUserEl;
import com.bobochen.easyblogback.eurekadatatemplate.entitiy.MyUserEntity;
import com.bobochen.easyblogback.eurekadatatemplate.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class MyTestController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MyUserDao myUserDao ;

    @Autowired

    private ElasticsearchTemplate esTemplate;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {

        return "hi " + name + ",i am from port:"+name ;
    }



    @RequestMapping("/hi2")
    public String home2(@RequestParam String name) {

        redisService.set("key1",name);
        return redisService.get("key1");
    }


    @RequestMapping("/hi3")
    public List<MyUserEntity> home3(@RequestParam String name) {

       return  myUserDao.searchUserName("1");
    }

    @RequestMapping("/hi4")
    public String  home4(@RequestParam String name) {
        String documentId = "123456";
        MyUserEl sampleEntity = new MyUserEl();
        sampleEntity.setGender("男");
        sampleEntity.setId(2);
        IndexQuery indexQuery = new IndexQueryBuilder().withId(sampleEntity.getId()+"").withObject(sampleEntity).build();
        esTemplate.index(indexQuery);

        return name;
    }


}
