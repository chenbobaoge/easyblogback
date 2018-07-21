package com.bobochen.easyblogback.allinone.controller;

import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ElasticSsearchController {

    @Autowired
    private TransportClient eclient;

    @GetMapping("/ecsetblog")
    public String ecsetblog(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("title", "红楼梦");
        map.put("contents", "重生之我是贾宝玉");
        map.put("createtime", "2018-01-01 02:02:02") ;
        map.put("tags", new String[]{"first","我是贾宝玉"}) ;
        map.put("readcount", 3);
        map.put("prices", 22.22);
        try {
            IndexResponse response = eclient.prepareIndex("blogsystem", "blog", "11")
                    .setSource(map).execute().actionGet();
            System.out.println("写入数据结果=" + response.status().getStatus() + "！id=" + response.getId());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return "ok";
    }

    @RequestMapping("/searchBlog")
    public String searchBlog() {
//        QueryBuilder qb = QueryBuilders.boolQuery().mu
        return "";
    }
}

