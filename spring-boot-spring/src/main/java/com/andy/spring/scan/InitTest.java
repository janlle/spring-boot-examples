package com.andy.spring.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author: Leone
 * @since: 2018-08-28
 **/
@Slf4j
@Service
public class InitTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init test");
    }


}
