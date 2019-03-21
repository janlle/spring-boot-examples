package com.andy.log.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
public class Test {

    private static final Logger COMMON_LOG = LoggerFactory.getLogger("common-log");

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            COMMON_LOG.info((1000 + i) + "," + RandomValue.randomGoods() + "," + RandomValue.random.nextInt(10000000));
        }

    }

}
