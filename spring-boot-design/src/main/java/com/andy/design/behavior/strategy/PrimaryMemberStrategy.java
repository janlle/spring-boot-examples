package com.andy.design.behavior.strategy;

/**
 * @author Leone
 * @since: 2018-08-03
 **/
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }

}