package com.andy.design.behavior.strategy;

public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param price 图书的原价
     * @return 计算出打折后的价格
     */
    double calcPrice(double price);
}