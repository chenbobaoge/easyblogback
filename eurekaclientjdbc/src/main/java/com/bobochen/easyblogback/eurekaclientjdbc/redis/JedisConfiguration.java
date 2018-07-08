package com.bobochen.easyblogback.eurekaclientjdbc.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;
import java.util.List;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;


@Configuration
public class JedisConfiguration {

    @Autowired
    private JedisProperties jedisProperties;

    @Bean
    public JedisPool getJedisPool() {

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        JedisPool jedisPool = null;
        if(jedisProperties.getPassword().equals("")) {
            jedisPool =new JedisPool(poolConfig, jedisProperties.getHost(), jedisProperties.getPort(),
                    jedisProperties.getTimeout(), jedisProperties.isSsl());
        }
        else
        {
            jedisPool =new JedisPool(poolConfig, jedisProperties.getHost(), jedisProperties.getPort(),
                    jedisProperties.getTimeout(), jedisProperties.getPassword(), jedisProperties.isSsl());
        }
        return jedisPool;
    }

//    @Bean
//    public JedisCluster getJedisCluster() {
//
//        List<String> nodesList = jedisProperties.getCluster().getNodes();
//        HashSet<HostAndPort> nodesSet = new HashSet<>();
//
//        if (nodesList != null && nodesList.size() > 0) {
//            for (String node : nodesList) {
//                if (node != null) {
//                    String[] hostAndPort = node.split(":");
//                    HostAndPort hostAndPort2 = new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
//                    nodesSet.add(hostAndPort2);
//                }
//            }
//        }
//
//        return new JedisCluster(nodesSet);
//
//    }
}
