package com.leone.boot.data.es;

import cn.hutool.core.util.IdUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.leone.boot.common.entity.User;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2021-07-24
 **/
public class Elastic8Client {

    private static final Logger log = LoggerFactory.getLogger(Elastic8Client.class);

    private static ElasticsearchClient client;

    static void init() throws IOException {
        String fingerprint = "12c5e8420e80aa2bdba6d06a8809a0b2628bad6b18a9cea9a0a215afe851698e";
        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerprint);

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456")
        );

        RestClient restClient = RestClient
                .builder(new HttpHost("127.0.0.1", 9200, "http"))
                .setHttpClientConfigCallback(hc -> hc
                        // .setSSLContext(sslContext)
                        // .setDefaultCredentialsProvider(credsProv)
                ).build();


        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
        log.info("elasticsearchClient = " + client);
        GetIndexResponse response = client.indices().get(builder -> builder.index("_all"));
        log.info(response.result().toString());
    }


    static void createIndex() throws IOException {
        CreateIndexResponse response = client.indices().create(c ->
                c.index("products").settings(IndexSettings.of(builder -> {
                    builder.numberOfReplicas("0");
                    builder.numberOfShards("1");
                    return builder;
                }))

        );
        // 响应状态
        boolean acknowledged = response.acknowledged();
        boolean shardsAcknowledged = response.shardsAcknowledged();
        String index = response.index();
        log.info("创建索引状态:{}", acknowledged);
        log.info("已确认的分片:{}", shardsAcknowledged);
        log.info("索引名称:{}", index);
    }

    static void getIndex() throws IOException {
        GetIndexResponse response = client.indices().get(builder -> builder.index("_all"));
        log.info(response.result().toString());
    }

    static void deleteIndex() throws IOException {
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(s -> s.index("products"));
        log.info("删除索引操作结果：{}", deleteIndexResponse.acknowledged());
    }


    static void addOneDocument() throws IOException {
        // 1、using the fluent DSL
        User user = new User();
        user.setUserId(IdUtil.getSnowflakeNextId());
        IndexResponse indexResponse = client.index(s ->
                s.index("products").id(user.getUserId().toString()).document(user)
        );
        log.info("result:{}", indexResponse.result().jsonValue());


        // 2、You can also assign objects created with the DSL to variables. Java API Client classes have a static of() method for this, that creates an object with the DSL syntax.
        // IndexRequest<Product> request = IndexRequest.of(i -> i
        //         .index("products")
        //         .id(product.getId())
        //         .document(product));
        // IndexResponse response = client.index(request);
        // log.info("Indexed with version " + response.version());

        // 3、Using classic builders
        // IndexRequest.Builder<Product> indexReqBuilder = new IndexRequest.Builder<>();
        // indexReqBuilder.index("users");
        // indexReqBuilder.id(product.getId());
        // indexReqBuilder.document(product);
        // IndexResponse responseTwo = client.index(indexReqBuilder.build());
        // log.info("Indexed with version " + responseTwo.version());
    }


    static void getDocument() throws IOException {
        // co.elastic.clients.elasticsearch.core.get.GetResult<TDocument>
        GetResponse<User> getResponse = client.get(s -> s.index("users").id("e051445c-ae8c-47ef-ab18-97b34025d49a"), User.class);
        log.info("getResponse:{}", getResponse.source());

        // Reading a domain object
        if (getResponse.found()) {
            User user = getResponse.source();
            assert user != null;
            log.info("user name={}", user.getAccount());
        }

        // 判断文档是否存在
        BooleanResponse booleanResponse = client.exists(s -> s.index("users").id("e051445c-ae8c-47ef-ab18-97b34025d49a"));
        log.info("判断Document是否存在:{}", booleanResponse.value());
    }

    public static void main(String[] args) throws IOException {
        init();
        // deleteIndex();
        createIndex();
        // getIndex();

        for (int i = 0; i < 10; i++) {
            addOneDocument();
        }


    }

}
