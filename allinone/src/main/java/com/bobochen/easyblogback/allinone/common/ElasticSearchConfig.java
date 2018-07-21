package com.bobochen.easyblogback.allinone.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "my.elasticsearch")
@Data
@Component
public class ElasticSearchConfig {
    private String clustername;
    private List<String> hostports;


    @Bean
    public TransportClient getClient(){
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name",clustername)
                    .build();


            TransportClient transportClient = new PreBuiltTransportClient(settings);

            for (String hostport:hostports
                 ) {
                transportClient=  transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostport.split(":")[0]), Integer.parseInt(hostport.split(":")[1])));
            }

            return transportClient;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }

    }
}
