package com.leone.boot.mvc.util;


import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-14
 **/
public class HttpBenchmark {

    private static final HttpClient client = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .connectTimeout(Duration.ofSeconds(1))
      .build();

    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    String url = randomUrl();
                    doGet(url, j, Thread.currentThread().getName());
                }
            }).start();
        }
    }

    public static String randomUrl() {
        return random.nextBoolean() ?
          "http://localhost:8080/lock/database" : "http://localhost:8081/lock/database";
    }


    public static void doGet(String url, int i, String threadName) {
        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .timeout(Duration.ofSeconds(60))
          .GET()
          .build();
        CompletableFuture<Void> voidCompletableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
          .thenApply(HttpResponse::body)
          .exceptionally(err -> "error: " + err.getMessage())
          .thenAccept(x -> System.out.println(url + "\t" + threadName + " " + x + " \t" + i));
        voidCompletableFuture.join();
    }

}
