package com.andy.mvc.service;

import com.andy.mvc.annocation.CustomerAnno;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 22:37
 **/
@CustomerAnno(value = "product")
public class ProductService {

    public void init(String name) {
        System.out.println("product:" + name);
    }

    public ProductService() {
        System.out.println("init....");
    }

}
