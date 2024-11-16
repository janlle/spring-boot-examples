package com.leone.test;

import com.leone.boot.mvc.MvcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MvcApplication.class})
public class UserBaseTest {

    @MockBean
    private RedissonClient redissonClient;

    @MockBean
    private CacheManager cacheManager;

    @Test
    public void test(){

    }





}