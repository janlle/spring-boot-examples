package com.andy.design.behavior.strategy;

/**
 * @author Leone
 * @since 2018-08-02
 **/
public class Price {
    
    //持有一个具体的策略对象
    private MemberStrategy strategy;

    /**
     * 构造函数，传入一个具体的策略对象
     *
     * @param strategy 具体的策略对象
     */
    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 计算图书的价格
     *
     * @param price 图书的原价
     * @return 计算出打折后的价格
     */
    public double quote(double price) {
        return this.strategy.calcPrice(price);
    }
}