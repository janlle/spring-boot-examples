package com.andy.spring.scan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author: Leone
 * @since: 2018-08-28
 **/
@Slf4j
@Component
public class InitTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init test");
    }


}
