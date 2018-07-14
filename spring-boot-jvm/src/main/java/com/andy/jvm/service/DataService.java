package com.andy.jvm.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-14 14:09
 **/
@Slf4j
@Data
public class DataService {

    private Boolean flag = true;

    public void addObjects(Integer count) throws Exception {
        log.info("count:{}", count);
        for (int i = 0; i < count; i++) {
            if (flag) {
                Thread.sleep(200);
                byte[][] bytes = new byte[1024][1024];
                break;
            }
        }
    }

    public void stop() {
        flag = false;
    }

}
