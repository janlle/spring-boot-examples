package com.leone.boot.log.task;

import com.leone.boot.log.app.AppLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
@Component
public class AppLogTask {

    private static final Logger JSON_LOG = LoggerFactory.getLogger("json-log");

    private static String url = "http://localhost:8081/api/log/app";

    @Autowired
    private RestTemplate restTemplate;

    private static HttpHeaders headers = new HttpHeaders();

    static {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    /**
     * 用户画像
     *
     * @Scheduled(fixedDelay = 50)
     */
    @Async
    @Scheduled(fixedDelay = 500)
    public void commonLog() {
        String log = AppLog.randomAppLog();
        HttpEntity<String> entity = new HttpEntity<>(log, headers);
        String result = restTemplate.postForObject(url, entity, String.class);
    }


}
