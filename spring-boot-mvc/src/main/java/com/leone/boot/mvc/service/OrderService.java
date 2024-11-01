package com.leone.boot.mvc.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
