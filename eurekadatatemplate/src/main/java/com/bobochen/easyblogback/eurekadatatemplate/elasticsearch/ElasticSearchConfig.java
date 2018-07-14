package com.bobochen.easyblogback.eurekadatatemplate.elasticsearch;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration

@Data
public class ElasticSearchConfig {
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clustername;// master node


    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusternodes;








    public TransportClient transportClient()  {
        try {

            Settings settings = Settings.builder()
                    .put("cluster.name",clustername)
                    .build();

            String host = clusternodes.split(":")[0];

            int port = Integer.valueOf(clusternodes.split(":")[1]).intValue();
            TransportClient transportClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));


//            if (host2 != null && !host2.equals("")) {
//                transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host2), port));
//            }
//            if (host3 != null && !host3.equals("")) {
//                transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host3), port));
//            }
            return transportClient;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Bean
    @Primary
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(transportClient());
    }

}
