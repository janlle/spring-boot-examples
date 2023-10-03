package com.leone.boot.mvc.service;

import com.leone.boot.mvc.anno.CustomerAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leone
 * @since 2018-06-29
 **/
@CustomerAnnotation(value = "productService")
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

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
