package com.leone.boot.spring.scan;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p>
 *
 * @author leone
 * @since 2018-08-28
 **/
//@Service
public class InitTest implements InitializingBean {

    public InitTest() {
        System.out.println("init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    public void init() {
        System.out.println("customer init");
    }


}
