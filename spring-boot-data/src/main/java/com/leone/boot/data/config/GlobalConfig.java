package com.leone.boot.data.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.*;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author leone
 * @since 2018-07-08
 **/
@Configuration
public class GlobalConfig {

    @Bean
    public Converter<String, Date> timestampConvert() {
        return source -> new Date(Long.parseLong(source));
    }

    @Bean
    public ElasticsearchClient esClient() {
        String serverUrl = "http://127.0.0.1:9200";
        String userName = "xxx";
        String password = "xxx";

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
          AuthScope.ANY, new UsernamePasswordCredentials(userName, password)
        );

        RestClient restClient = RestClient
          .builder(HttpHost.create(serverUrl))
          .setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credsProv))
          .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    //@Bean
    public KeyGenerator keyGenerator() {
        KeyGenerator keyGenerator = (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
        return keyGenerator;
    }



}
