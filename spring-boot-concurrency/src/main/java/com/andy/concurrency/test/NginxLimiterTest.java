package com.andy.concurrency.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-09
 **/
public class NginxLimiterTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            CompletableFuture.supplyAsync(() -> {
                final ResponseEntity<String> entity = new RestTemplate().getForEntity("http://39.108.125.41:8761/test", String.class);
                return entity.getBody();
            }, service).thenAccept(System.out::println);
        }
        service.shutdown();
    }
}