package com.leone.boot.mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author leone
 * @since 2018-06-29
 **/
@Slf4j
@Service
public class OrderService {

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
