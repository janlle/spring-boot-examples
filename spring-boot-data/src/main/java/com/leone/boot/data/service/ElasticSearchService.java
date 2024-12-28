package com.leone.boot.data.service;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.google.gson.Gson;
// import com.leone.bigdata.database.bean.Product;
// import com.leone.bigdata.database.util.RandomValue;
// import org.apache.http.HttpHost;
// import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
// import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
// import org.elasticsearch.action.bulk.BulkRequest;
// import org.elasticsearch.action.bulk.BulkResponse;
// import org.elasticsearch.action.get.GetRequest;
// import org.elasticsearch.action.index.IndexRequest;
// import org.elasticsearch.action.index.IndexResponse;
// import org.elasticsearch.action.search.SearchRequest;
// import org.elasticsearch.action.search.SearchResponse;
// import org.elasticsearch.action.support.master.AcknowledgedResponse;
// import org.elasticsearch.client.RequestOptions;
// import org.elasticsearch.client.RestClient;
// import org.elasticsearch.client.RestClientBuilder;
// import org.elasticsearch.client.RestHighLevelClient;
// import org.elasticsearch.client.indices.CreateIndexRequest;
// import org.elasticsearch.client.indices.CreateIndexResponse;
// import org.elasticsearch.client.indices.GetIndexRequest;
// import org.elasticsearch.common.geo.GeoPoint;
// import org.elasticsearch.common.settings.Settings;
// import org.elasticsearch.common.unit.DistanceUnit;
// import org.elasticsearch.common.unit.TimeValue;
// import org.elasticsearch.common.xcontent.XContentType;
// import org.elasticsearch.geometry.MultiPoint;
// import org.elasticsearch.geometry.Point;
// import org.elasticsearch.index.query.BoolQueryBuilder;
// import org.elasticsearch.index.query.QueryBuilder;
// import org.elasticsearch.index.query.QueryBuilders;
// import org.elasticsearch.index.query.RangeQueryBuilder;
// import org.elasticsearch.search.SearchHit;
// import org.elasticsearch.search.aggregations.AggregationBuilder;
// import org.elasticsearch.search.aggregations.AggregationBuilders;
// import org.elasticsearch.search.builder.SearchSourceBuilder;
// import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.util.ResourceUtils;
//
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.concurrent.TimeUnit;
// import java.util.stream.Collectors;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@Configuration
 *
 * @author leone
 * @since 2019-06-27
 **/
@Service
public class ElasticSearchService {

    @Autowired
    private ObjectMapper objectMapper;

    public Gson gson = new Gson();

    private final String indexName = "product";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    // private RestHighLevelClient client;

    // @Value("${elasticsearch.urls}")
    // private String urls;

    // // @PostConstruct
    // public void restClientBuilder() {
    //     HttpHost[] httpHosts = Arrays.stream(urls.split(",")).map(HttpHost::create).toArray(HttpHost[]::new);
    //
    //     RestClientBuilder builder = RestClient.builder(httpHosts);
    //     client = new RestHighLevelClient(builder);
    // }


    // /**
    //  * 从mysql中导入到ES
    //  *
    //  * @return
    //  */
    // public Integer mock(int size) {
    //     if (!isExistsIndex(indexName)) {
    //         try {
    //             Path path = ResourceUtils.getFile("classpath:mapping.json").toPath();
    //             String mapping = Files.lines(path).collect(Collectors.joining(System.lineSeparator()));
    //             createIndex(indexName, 3, 1, mapping);
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //
    //     // mock data 批量插入
    //     BulkRequest bulkRequest = new BulkRequest();
    //     for (int i = 0; i < size; i++) {
    //         Product product = new Product(RandomValue.strId(), RandomValue.goods(),
    //                 RandomValue.randomTitle(), RandomValue.decimal(), RandomValue.randomKeyWords(),
    //                 RandomValue.RANDOM.nextLong(), RandomValue.dateTimeStr(), RandomValue.randomLonLat());
    //
    //         IndexRequest request = new IndexRequest(indexName).source(gson.toJson(product), XContentType.JSON);
    //         bulkRequest.add(request);
    //         try {
    //             if (i % 20 == 0) {
    //                 BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
    //                 System.out.println(bulk.status().toString() + " " + bulk.getIngestTook());
    //                 bulkRequest = new BulkRequest();
    //             }
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return size;
    // }
    //
    // /**
    //  * 关键词会分词
    //  * QueryBuilders.matchQuery("content", content);
    //  * 关键词不会分词
    //  * QueryBuilders.matchPhraseQuery("content", content);
    //  * 关键词不分词
    //  * QueryBuilders.termQuery("title", key);
    //  * 范围查询
    //  * QueryBuilders.rangeQuery("categoryId").gt("1000").lt("2000");
    //  * <p>
    //  * QueryBuilders.boolQuery() 包括 must、filter、should、must_not
    //  *
    //  * @param key
    //  * @return
    //  */
    // public List<Product> search(String key) {
    //     List<Product> result = new ArrayList<>();
    //
    //     SearchRequest searchRequest = new SearchRequest(indexName);
    //
    //     SearchSourceBuilder ssb = new SearchSourceBuilder();
    //     QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title", key));
    //
    //     ssb.query(qb);
    //     ssb.from(0);
    //     ssb.size(100);
    //     ssb.timeout(new TimeValue(3, TimeUnit.SECONDS));
    //     searchRequest.source(ssb);
    //
    //     // 低代码
    //     try {
    //         SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
    //         SearchHit[] hits = response.getHits().getHits();
    //         for (SearchHit hit : hits) {
    //             Product product = objectMapper.readValue(hit.getSourceAsString(), Product.class);
    //             result.add(product);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return result;
    // }
    //
    // /**
    //  * 如果文档存在，则existsAPI返回true，否则返回false
    //  *
    //  * @param id
    //  * @return
    //  */
    // public boolean exists(String id) {
    //     GetRequest request = new GetRequest(indexName, id);
    //     // 禁用提取_source
    //     request.fetchSourceContext(new FetchSourceContext(false));
    //     // 禁用提取存储的字段
    //     request.storedFields("_none_");
    //     try {
    //         return client.exists(request, RequestOptions.DEFAULT);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    //
    // /**
    //  * 根据商品id创建商品
    //  *
    //  * @param p
    //  * @return
    //  */
    // public int create(Product p) {
    //     IndexRequest indexRequest = new IndexRequest(indexName);
    //     try {
    //         indexRequest.source(gson.toJson(p), XContentType.JSON);
    //         IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    //         System.out.println(indexResponse.status() + " " + indexResponse.toString());
    //         return indexResponse.status().getStatus();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return 0;
    // }
    //
    // public String aggr(String field) {
    //     SearchRequest searchRequest = new SearchRequest(indexName);
    //
    //     // 普通查询
    //     BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
    //     RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("saleNum")
    //             .gt(1)
    //             .lt(10000);
    //     boolBuilder.must(rangeQueryBuilder);
    //
    //     // 按分类聚合
    //     AggregationBuilder aggregation = AggregationBuilders
    //             .terms("category_num")
    //             .field("category");
    //
    //     // 构建查询
    //     SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(boolBuilder).size(0).aggregation(aggregation);
    //
    //     searchRequest.source(sourceBuilder);
    //     //发送请求
    //     SearchResponse searchResponse;
    //     try {
    //         searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    //         System.out.println(searchResponse);
    //         return searchResponse.toString();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
    //
    // /**
    //  * 经纬度查询
    //  *
    //  * @param lat 经度
    //  * @param lon 维度
    //  */
    // public String locationSearch(double lat, double lon, String distance) throws IOException {
    //     SearchRequest request = new SearchRequest();
    //     SearchSourceBuilder query = new SearchSourceBuilder();
    //
    //     // distance query 查找距离中心点范围内的点
    //     QueryBuilder location = QueryBuilders.geoDistanceQuery("location")
    //             .point(new GeoPoint(lat, lon))
    //             .distance(distance, DistanceUnit.KILOMETERS);
    //
    //     // bounding query 查找指定点组成的矩形范围内的点
    //     QueryBuilders.geoBoundingBoxQuery("location").setCorners(new GeoPoint(lat, lon), new GeoPoint(lat, lon));
    //
    //     // polygon query 查找多个点组成的一个多边形中的点
    //     QueryBuilders.geoIntersectionQuery("location", new MultiPoint(Arrays.asList(new Point(lat, lon), new Point(lat, lon), new Point(lat, lon))));
    //
    //     query.query(location);
    //
    //     request.source(query);
    //
    //     try {
    //         SearchResponse search = client.search(request, RequestOptions.DEFAULT);
    //         System.out.println(search);
    //         return search.toString();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
    //
    // // ------------------------------------------------------------
    //
    // /**
    //  * @param indexName
    //  * @param shards
    //  * @param replicas
    //  * @param jsonStr
    //  * @return
    //  */
    // public boolean createIndex(String indexName, Integer shards, Integer replicas, String jsonStr) {
    //     if (isExistsIndex(indexName)) {
    //         return false;
    //     }
    //     Settings settings = Settings.builder()
    //             .put("index.number_of_shards", shards)
    //             .put("index.number_of_replicas", replicas)
    //             .put("index.blocks.read_only_allow_delete", false)
    //             .put("analysis.analyzer.default.tokenizer", "standard")
    //             .put("index.max_result_window", 100000000).build();
    //
    //     CreateIndexRequest request = new CreateIndexRequest(indexName).settings(settings);
    //     request.mapping(jsonStr, XContentType.JSON);
    //     try {
    //         CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
    //         return createIndexResponse.isAcknowledged();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    //
    // /**
    //  * @param indexName
    //  * @return
    //  */
    // public boolean deleteIndex(String... indexName) {
    //     if (!isExistsIndex(indexName)) {
    //         return false;
    //     }
    //     DeleteIndexRequest request = new DeleteIndexRequest(indexName);
    //     try {
    //         AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
    //         return delete.isAcknowledged();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    //
    //
    // /**
    //  * @param indexName
    //  * @return
    //  */
    // public boolean isExistsIndex(String... indexName) {
    //     GetIndexRequest request = new GetIndexRequest(indexName);
    //     try {
    //         return client.indices().exists(request, RequestOptions.DEFAULT);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    //
    //
    // public String[] getIndexByPrefix(String prefix, String excludeIndex) {
    //     GetIndexRequest request = new GetIndexRequest(prefix + "*");
    //     try {
    //         String[] indices = client.indices().get(request, RequestOptions.DEFAULT).getIndices();
    //         if (excludeIndex != null) {
    //             List<String> list = new ArrayList<>();
    //             for (String index : indices) {
    //                 if (!index.equals(excludeIndex)) {
    //                     list.add(index);
    //                 }
    //             }
    //             return list.toArray(new String[0]);
    //         }
    //         return indices;
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
    //
    //
    // /**
    //  * @param oldIndex oldIndex
    //  * @param newIndex newIndex
    //  * @param alias    alias
    //  * @return bool
    //  */
    // public boolean updateAlias(String oldIndex, String newIndex, String alias) {
    //
    //     IndicesAliasesRequest request = new IndicesAliasesRequest();
    //     if (oldIndex != null && isExistsIndex(oldIndex)) {
    //         IndicesAliasesRequest.AliasActions removeAlias = IndicesAliasesRequest.AliasActions.remove();
    //         removeAlias.alias(alias);
    //         removeAlias.index(oldIndex);
    //         request.addAliasAction(removeAlias);
    //     }
    //
    //     if (newIndex != null && isExistsIndex(newIndex)) {
    //         IndicesAliasesRequest.AliasActions addAlias = IndicesAliasesRequest.AliasActions.add();
    //         addAlias.alias(alias);
    //         addAlias.index(newIndex);
    //         request.addAliasAction(addAlias);
    //     }
    //     try {
    //         return client.indices().updateAliases(request, RequestOptions.DEFAULT).isAcknowledged();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    //
    //
    // /**
    //  * @param prefix
    //  * @param excludeIndex
    //  * @return
    //  */
    // public boolean deleteIndexByPrefix(String prefix, String excludeIndex) {
    //     String[] indexArr = getIndexByPrefix(prefix, excludeIndex);
    //     return deleteIndex(indexArr);
    // }

}
