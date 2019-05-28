package com.leone.boot.common.design.behavior.strategy;

/**
 * <p> 
 *
 * @author leone
 * @since 2019-05-28
 **/
public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param price 图书的原价
     * @return 计算出打折后的价格
     */
    double calcPrice(double price);
}