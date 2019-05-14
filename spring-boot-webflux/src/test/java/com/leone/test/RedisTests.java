package com.leone.test;

import com.leone.boot.flux.FluxApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.ByteBuffer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxApplication.class)
public class RedisTests {

    @Autowired
    private ReactiveRedisConnection connection;

    @Test
    public void testRedis(){
        connection.stringCommands().set(ByteBuffer.wrap("h".getBytes()), ByteBuffer.wrap("w".getBytes())).subscribe(System.out::println);
    }
}