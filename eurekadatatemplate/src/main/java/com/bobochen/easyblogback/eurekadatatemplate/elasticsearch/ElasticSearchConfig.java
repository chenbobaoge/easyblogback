package com.bobochen.easyblogback.eurekadatatemplate.elasticsearch;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.bobochen")
public class ElasticSearchConfig {
    @Value("${elasticsearch.host1}")
    private String esHost;// master node

    @Value("${elasticsearch.host2:}")
    private String esHost2;//replica node

    @Value("${elasticsearch.host3:}")
    private String esHost3;//replica node

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Bean
    public TransportClient transportClient() throws Exception {

        Settings settings = Settings.builder()
                .put("cluster.name", esClusterName)
                .build();

        TransportClient transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));


        if (esHost2!=null && !esHost2.equals("")) {
            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost2), esPort));
        }
        if (esHost3!=null && !esHost3.equals("")) {
            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost3), esPort));
        }
        return transportClient;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(TransportClient client) throws Exception {
        return new ElasticsearchTemplate(client);
    }

}
