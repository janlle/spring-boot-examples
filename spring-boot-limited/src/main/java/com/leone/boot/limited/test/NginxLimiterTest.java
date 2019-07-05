package com.leone.boot.limited.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-09
 **/
public class NginxLimiterTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        nginxTest();
    }

    public static void nginxTest() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            CompletableFuture.supplyAsync(() -> {
                final ResponseEntity<String> entity = new RestTemplate().getForEntity("http://ip:8761/test", String.class);
                return entity.getBody();
            }, service).thenAccept(System.out::println);
        }
        service.shutdown();
    }

    public static void test() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 6; i++) {
            String result = restTemplate.getForObject("http://ip:8080/test", String.class);
            System.out.println(result);
        }
    }


}