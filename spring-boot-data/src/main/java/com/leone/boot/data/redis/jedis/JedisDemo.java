package com.leone.boot.data.redis.jedis;

import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-19
 **/
public class JedisDemo {

    // Jedis 连接池实例
    private JedisPool jedisPool;

    /**
     * 初始化，设置参数
     */
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(32);
        // 闲置最大连接数
        config.setMaxIdle(6);
        // 闲置最小连接数
        config.setMinIdle(0);
        // 到达最大连接数后，调用者阻塞时间
        config.setMaxWaitMillis(15000);
        // 连接空闲的最小时间，可能被移除
        config.setMinEvictableIdleTimeMillis(300000);
        // 连接空闲的最小时间，多余最小闲置连接的将被移除
        config.setSoftMinEvictableIdleTimeMillis(-1);
        // 设置每次检查闲置的个数
        config.setNumTestsPerEvictionRun(3);
        // 申请连接时，是否检查连接有效
        config.setTestOnBorrow(false);
        // 返回连接时，是否检查连接有效
        config.setTestOnReturn(false);
        // 空闲超时,是否执行检查有效
        config.setTestWhileIdle(false);
        // 空闲检查时间
        config.setTimeBetweenEvictionRunsMillis(60000);
        // 当连接数耗尽，是否阻塞
        config.setBlockWhenExhausted(true);
        // 连接池配置对象 (config, host + port + timeout + password + db)
        jedisPool = new JedisPool(config, "localhost", 6379, 10000, null, 0);
    }


    /**
     * set and get string
     */
    public void stringTest() {
        Jedis jedis = jedisPool.getResource();
        // set:返回操作结果
        System.out.println("name=>wsy:" + jedis.set("name", "wsy"));

        // get:value
        System.out.println("name:" + jedis.get("name"));

        // append:字符串长度
        System.out.println("append:" + jedis.append("name", "_ss"));

        // strLength:字符串长度
        System.out.println("strLength:" + jedis.strlen("name"));

        // getrange:返回不包括起始坐标的值
        System.out.println("getrange:" + jedis.getrange("name", 10, 13));

        // setrange:从起始坐标考试替换，未替换的保持
        System.out.println("setRange:" + jedis.setrange("name", 10, "#"));

        // mset:批量设置，返回批量设置结果
        System.out.println("mset:" + jedis.mset("name", "wsy", "age", "29"));

        // mget:返回数组
        System.out.println("mget:" + jedis.mget("name", "age"));

        // incr:value自增1后，返回value
        System.out.println("incr:" + jedis.incr("age"));

        // incr:value自增传参值后，返回value
        System.out.println("incrBy:" + jedis.incrBy("age", 3));

        // decr:value自减1，返回value
        System.out.println("decr:" + jedis.decr("age"));

        // decrBy:value自减入参值，返回value
        System.out.println("decrBy:" + jedis.decrBy("age", 3));

        // setex:设置key值+有效时间，如果key存在则覆盖value
        System.out.println("setex:" + jedis.setex("phone", 10, "13600000001"));

        // setnx:当key不存在时，设置才成功
        System.out.println("setnx:" + jedis.setnx("address", "china"));

        // del:删除对应key
        System.out.println("del:" + jedis.del("address1"));
    }


    /**
     * redis中hash类型常用操作
     */
    public void hashTest() {
        Jedis jedis = jedisPool.getResource();
        // hset:返回值为key为新返回1，为旧覆盖旧值返回0
        System.out.println("hset:" + jedis.hset("user", "name", "wangshaoyi"));

        Map<String, String> map = new HashMap<>();

        map.put("name", "wsy");
        map.put("age", "29");

        // hmset:map对象
        System.out.println("hmset:" + jedis.hmset("user", map));

        // hexists:判断hashmap中key是否存在
        System.out.println("hexists:" + jedis.hexists("user", "age"));

        // hget:获取map中key对应的value
        System.out.println("hget:" + jedis.hget("user", "name"));

        // hgetAll:获取map中所有对象
        System.out.println("hgetAll:" + jedis.hgetAll("user"));

        // hkeys:获取map中所有key
        System.out.println("hkeys:" + jedis.hkeys("user"));

        // hvals:获取map中所有value
        System.out.println("hvals:" + jedis.hvals("user"));


        // hmget:批量获取keys的对象，返回List
        System.out.println("hmget:" + jedis.hmget("user", "age", "name"));

        // hlen:map的大小
        System.out.println("hlen:" + jedis.hlen("user"));

        // hdel:删除map中对应key,正确删除返回1
        System.out.println("hdel:" + jedis.hdel("user", "age0"));
    }

    /**
     * redis中 list 类型常用操作
     */
    public void listTest() {
        Jedis jedis = jedisPool.getResource();

        // lpush:批量头部插入，返回List的size
        System.out.println("lpush:" + jedis.lpush("contacts", "xx", "yy", "zz"));

        // lpushx:单个头部插入，返回List的size
        System.out.println("lpushx:" + jedis.lpushx("contacts", "aa"));

        // linsert:指定对象位置(前or后)插入
        System.out.println("linsert:" + jedis.linsert("contacts", ListPosition.AFTER, "zz", "bb"));

        // lset:将指定的位置设置值（替换旧值）
        System.out.println("lset:" + jedis.lset("contacts", 2, "cc"));

        // lpop:链表头的对象
        System.out.println("lpop:" + jedis.lpop("contacts"));

        // lrange:获取list指定start、end位置value
        System.out.println("lrange:" + jedis.lrange("contacts", 1, 3));

        // ltrim:只剩start\end中list值，其余删除
        System.out.println("ltrim:" + jedis.ltrim("contacts", 1, 3));

        // lrem:删除list指定值（次数指定），返回删除个数
        System.out.println("lrem:" + jedis.lrem("contacts", 2, "yy"));

        // rpoplpush:将源list尾部对象移到目标list对象头部
        System.out.println("rpoplpush:" + jedis.rpoplpush("contacts", "contacts_old"));

        // rpush:在list尾部对象添加值
        System.out.println("rpush:" + jedis.rpush("contacts", "aa", "bb"));

        // rpop:移除在list尾部值，返回移除的对象
        System.out.println("rpop:" + jedis.rpop("contacts"));

        // brpop:阻塞尾部对象抛出，指定超时时间，返回抛出值
        System.out.println("brpop:" + jedis.brpop(1, "contacts"));

        System.out.println("blpop:" + jedis.blpop(1, "contacts"));

        System.out.println("blpop（阻塞1秒返回）:" + jedis.blpop(1, "contacts"));
    }

    /**
     * redis中 set 类型常用操作
     */
    public void setTest() {
        Jedis jedis = jedisPool.getResource();

        // sadd:集合添加元素,返回添加成功后数据
        System.out.println("sadd:" + jedis.sadd("phones", "13600000001", "13300000001"));
        System.out.println("sadd:" + jedis.sadd("phones", "13600000002", "13300000002"));

        // scard:返回集合中元素数
        System.out.println("scard:" + jedis.scard("phones"));

        jedis.sadd("phones_old", "13600000002");
        jedis.sadd("phones_old_1", "13300000001");

        // sdiff:首set与其他set之间的差集，返回差集值
        System.out.println("sdiff:" + jedis.sdiff("phones", "phones_old", "phones_old_1"));

        // sdiffstore:首set与其他set之间的差集保存至新set，返回差集数
        System.out.println("sdiffstore:" + jedis.sdiffstore("phones_new", "phones", "phones_old"));

        // sinter:返回集合的交集
        System.out.println("sinter:" + jedis.sinter("phones", "phones_new"));

        // sismember:判断value是否为set的值
        System.out.println("sismember:" + jedis.sismember("phones", "13600000001"));

        // smembers:返回集合中成员
        System.out.println("smembers:" + jedis.smembers("phones"));

        // smove:将首源set中元素移动目标set，返回移动数
        System.out.println("smove:" + jedis.smove("phones", "phones_new", "13600000002"));

        // spop:随机移除set的一元素，返回移除元素
        System.out.println("spop:" + jedis.spop("phones"));

        // srandmember:随机取出集合中一个元素
        System.out.println("srandmember:" + jedis.srandmember("phones_new"));

        // srem:删除集合中指定元素
        System.out.println("srem:" + jedis.srem("phones_new", "13600000002"));

        // sunion:集合中并集
        System.out.println("sunion:" + jedis.sunion("phones", "phones_new", "phones_old"));

    }

    /**
     * redis中 SortedSet 类型常用操作
     */
    public void sortedSetTest() {
        Jedis jedis = jedisPool.getResource();

        // zadd:sortedSet添加元素
        System.out.println("zadd:" + jedis.zadd("scores", 610.5, "xx"));
        jedis.zadd("scores", 630, "yy");

        // zcard:返回sortedset中元素数
        System.out.println("zcard:" + jedis.zcard("scores"));

        // zcount:返回指定分值（包括）的元素数
        System.out.println("zcount:" + jedis.zcount("scores", 610, 620));

        // zincrby:将指定值分数加分，返回加后的分数
        System.out.println("zincrby:" + jedis.zincrby("scores", 10, "xx"));

        // zrange:返回指定坐标的值
        System.out.println("zrange:" + jedis.zrange("scores", 0, 1));

        // zrangeByScore:返回指定分数范围内的对象
        System.out.println("zrangeByScore:" + jedis.zrangeByScore("scores", 600, 700));

        // zrank:返回指定值的位置（分数低->高，0开始）
        System.out.println("zrank:" + jedis.zrank("scores", "yy"));

        // zrevrank:返回指定值的位置（分数高->低，0开始）
        System.out.println("zrevrank:" + jedis.zrevrank("scores", "yy"));


        // zrem:删除，其中还有zremrangeByRank\zremrangeByScore
        System.out.println("zrem:" + jedis.zrem("scores", "yy"));

        jedis.zadd("scores", 630, "yy");
        jedis.zadd("scores", 640, "zz");
        // zrevrange：获取指定位置数据（分数从高->低）
        System.out.println(":" + jedis.zrevrange("scores", 0, 1));

        System.out.println("zrangeByScoreWithScores:" + jedis.zrangeByScoreWithScores("scores", 600, 700));

        // zscore:获取指定分数
        System.out.println("zscore:" + jedis.zscore("scores", "xx"));
        jedis.zadd("scores_1", 630.5, "xx");
        jedis.zadd("scores_1", 610.5, "bb");
        jedis.zadd("scores_1", 622.5, "cc");

        // zunionstore:sortedset集合的并集并保存,如果集合中元素相同，则分数相加
        System.out.println("zunionstore:" + jedis.zunionstore("score_total", "scores", "scores_1"));

        ZParams zParams = new ZParams();
        // 指定分数操作：+，最小，最大
        zParams.aggregate(ZParams.Aggregate.MAX);
        // 分数中的乘法因子
        zParams.weights(1, 0.1);

        System.out.println("zunionstore:" + jedis.zunionstore("score_max", zParams, "scores", "scores_1"));

        // zinterstore:集合元素取交集，相同元素值相加(默认)
        System.out.println("zinterstore:" + jedis.zinterstore("score_inter", "scores", "scores_1"));
    }

}
