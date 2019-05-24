package com.leone.boot.mvc.service;

import com.leone.boot.mvc.anno.CustomerAnnotation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leone
 * @since 2018-06-29
 **/
@Slf4j
@CustomerAnnotation(value = "productService")
public class ProductService {

    public void init() {
        log.info("ProductService @PostConstruct...");
    }

    public ProductService() {
        log.info("ProductService constructor...");
    }

    public void destroy() {
        log.info("ProductService @PreDestroy...");
    }

}
