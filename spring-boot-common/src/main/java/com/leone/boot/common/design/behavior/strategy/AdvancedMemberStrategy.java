package com.leone.boot.common.design.behavior.strategy;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为80%");
        return booksPrice * 0.8;
    }
}