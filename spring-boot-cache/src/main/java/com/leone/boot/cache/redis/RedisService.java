package com.leone.boot.cache.redis;


import cn.hutool.core.util.IdUtil;
import com.leone.boot.common.util.EntityFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2018-08-11
 **/
@Service
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.prefix}")
    private String redisPrefix;

    @Cacheable(value = "userCache")
    public String userCache() {
        System.out.println("no cache");
        return "当前系统时间:" + System.currentTimeMillis();
    }

    /**
     * 相当于队列操作
     */
    public long list(int count) {
        log.info("list:{}", count);
        for (int i = 0; i < count; i++) {
            String key = "list-key", value = IdUtil.fastSimpleUUID() + i;
            Long push = redisTemplate.opsForList().leftPush(key, value);
            // Long push = redisTemplate.opsForList().rightPush(key, value);
            log.info("leftPush key:[{}] -- value:[{}]", key, value);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < count; i++) {
            Object result = redisTemplate.opsForList().rightPop("list-key");
            log.info("leftPop key:[{}] -- value:[{}]", i, result);
        }
        return count;
    }

    /**
     * string 普通字符串或对象
     */
    public long string() {
        log.info("value:{}", 1);
        redisTemplate.opsForValue().set("set-key", EntityFactory.getUser(), 60, TimeUnit.SECONDS);
        return 1;
    }


    /**
     * set 操作
     */
    public long set() {
        log.info("set:{}", 1);
        // set值
        Long add = redisTemplate.opsForSet().add("key1", "dd", "bb", "ee", "aa");

        // 获取值
        Set<Object> resultSet = redisTemplate.opsForSet().members("key1");

        // 获取变量中值的长度
        Long size = redisTemplate.opsForSet().size("key1");

        // 随机获取变量中的元素
        Object randomVal = redisTemplate.opsForSet().randomMember("key1");

        // 随机获取变量中指定个数的元素
        List<Object> randomValues = redisTemplate.opsForSet().randomMembers("key1", 3);

        //  检查给定的元素是否在变量中
        Boolean isMember = redisTemplate.opsForSet().isMember("key1", "ff");

        // 转移变量的元素值到目的变量
        //Boolean move = redisTemplate.opsForSet().move("key1", "aa", "ee");

        // 弹出变量中的元素
        //List<Object> key1 = redisTemplate.opsForSet().pop("key1", 1);

        // 批量移除变量中的元素。
        //Long remove = redisTemplate.opsForSet().remove("key1", "aa", "bb");

        // 匹配获取键值对，ScanOptions.NONE为获取全部键值对；ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
        /*Cursor<Object> cursor = redisTemplate.opsForSet().scan("setValue", ScanOptions.scanOptions().match("c").build());
        while (cursor.hasNext()){
            Object object = cursor.next();
            System.out.println("通过scan(K key, ScanOptions options)方法获取匹配的值:" + object);
        }*/

        log.info("size: {} randomVal: {} randomValues: {} resultSet: {} isMember: {}", size, randomVal, randomValues, resultSet, isMember);
        return 1;
    }


    public long zSet() {
        log.info("zSet:{}", 1);
        redisTemplate.opsForZSet().add(SpringRedisConfig.userCatch(IdUtil.nanoId(9)), EntityFactory.getUser(), 3);

        redisTemplate.opsForZSet().add("zSetValue", "A", 1);
        redisTemplate.opsForZSet().add("zSetValue", "B", 3);
        redisTemplate.opsForZSet().add("zSetValue", "C", 2);
        redisTemplate.opsForZSet().add("zSetValue", "D", 5);

        // 获取指定区间的元素
        Set<Object> zSetValue = redisTemplate.opsForZSet().range("zSetValue", 0, -1);
        log.info("range: {}", zSetValue);


        // 用于获取满足非score的排序取值。这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定。
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // range.gt("A");
        range.lt("D");
        //zSetValue = redisTemplate.opsForZSet().rangeByLex("zSetValue", range);
        log.info("rangeByLex: {}", zSetValue);

        // 通过TypedTuple方式新增数据
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<>("E", 6.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<>("F", 7.0);
        ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<>("G", 5.0);
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<>();

        typedTupleSet.add(typedTuple1);
        typedTupleSet.add(typedTuple2);
        typedTupleSet.add(typedTuple3);
        redisTemplate.opsForZSet().add("typedTupleSet", typedTupleSet);
        zSetValue = redisTemplate.opsForZSet().range("typedTupleSet", 0, -1);
        log.info("添加元素:{}", zSetValue);

        Long count = redisTemplate.opsForZSet().count("zSetValue", 1, 5);
        log.info("获取区间值的个数:{}", count);

        return 1;
    }

    /**
     * hash 操作
     */
    public long hash() {
        log.info("hash:{}", 1);
        // put() 添加
        redisTemplate.opsForHash().put("he1", "key1", EntityFactory.getUser());
        redisTemplate.opsForHash().put("he1", "key2", EntityFactory.getUser());
        redisTemplate.opsForHash().put("he1", "key3", EntityFactory.getUser());
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("he1");
        log.info("put:{}", entries);

        // putAll() 批量添加
        Map<String, Object> param = new HashMap<>();
        param.put("key1", EntityFactory.getUser());
        param.put("key2", EntityFactory.getUser());
        param.put("key3", EntityFactory.getUser());
        redisTemplate.opsForHash().putAll("he2", param);
        log.info("putAll:{}", redisTemplate.opsForHash().entries("he2"));

        // delete() 删除指定key
        redisTemplate.opsForHash().delete("he3", "key1");
        log.info("delete:{}", redisTemplate.opsForHash().entries("he3"));

        // hasKey() 判断某个key是否存在
        log.info("hasKey:{}", redisTemplate.opsForHash().hasKey("he2", "key2"));

        // get() 获取值
        log.info("get:{}", redisTemplate.opsForHash().get("he2", "key1"));

        // multiGet() 批量获取
        log.info("multiGet:{}", redisTemplate.opsForHash().multiGet("he2", Arrays.asList("key1", "key2")));

        // 获取所有key
        log.info("keys:{}", redisTemplate.opsForHash().keys("he2"));

        // 获取所有key size
        log.info("size:{}", redisTemplate.opsForHash().size("he2"));

        // 获取所有key values
        log.info("values:{}", redisTemplate.opsForHash().values("he2"));

        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan("he2", ScanOptions.NONE);
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            log.info("key:{} -- value:{}", entry.getKey(), entry.getValue());
        }
        return 1;
    }


}
