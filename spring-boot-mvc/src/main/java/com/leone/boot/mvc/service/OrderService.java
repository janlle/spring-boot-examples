package com.leone.boot.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author leone
 * @since 2018-06-29
 **/
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @PostConstruct
    public void init() {
        log.info("OrderService @PostConstruct...");
    }

    public OrderService() {
        log.info("OrderService constructor...");
    }

    @PreDestroy
    public void destroy() {
        log.info("OrderService @PreDestroy...");
    }

}
