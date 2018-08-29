package com.andy.spring.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author: Leone
 * @since: 2018-08-28
 **/
@Slf4j
//@Service
public class InitTest implements InitializingBean {

    public InitTest() {
        System.out.println("init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init test");
    }

    public void init() {
        System.out.println("customer init");
    }


}
