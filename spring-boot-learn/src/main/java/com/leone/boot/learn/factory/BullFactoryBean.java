package com.leone.boot.learn.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-22
 **/
public class BullFactoryBean implements FactoryBean<BullFactoryBean> {

    private String data;

    public BullFactoryBean() {
        this.data = "通过无参构造方法创建实例";
    }

    public BullFactoryBean(String data) {
        this.data = data;
    }

    @Override
    public BullFactoryBean getObject() throws Exception {
        return new BullFactoryBean("通过FactoryBean.getObject()创建实例");
    }

    @Override
    public Class<?> getObjectType() {
        return BullFactoryBean.class;
    }

    public String getData() {
        return data;
    }

}
