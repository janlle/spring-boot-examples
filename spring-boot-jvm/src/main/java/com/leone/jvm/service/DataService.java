package com.leone.jvm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Leone
 * @since 2018-07-14
 **/
@Slf4j
@Service
public class DataService {

    private Boolean flag = true;

    public void addObjects(Integer count) throws Exception {
        for (int i = 0; i < count; i++) {
            if (flag) {
                Thread.sleep(200);
                byte[][] bytes = new byte[1024][1024];
                log.info("count: {} current: {}", count, i);
            }
            System.out.println(i);
        }
    }

    public void stop() {
        flag = false;
    }

}
