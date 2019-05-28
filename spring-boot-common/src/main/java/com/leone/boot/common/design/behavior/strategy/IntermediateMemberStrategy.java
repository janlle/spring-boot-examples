package com.leone.boot.common.design.behavior.strategy;

public class IntermediateMemberStrategy implements MemberStrategy {

    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为90%");
        return booksPrice * 0.9;
    }
}