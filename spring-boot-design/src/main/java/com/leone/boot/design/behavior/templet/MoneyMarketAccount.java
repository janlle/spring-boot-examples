package com.leone.boot.design.behavior.templet;
/**
 * @author leone
 * @since 2018-08-03
 **/
public class MoneyMarketAccount extends Account {

    @Override
    protected String doCalculateAccountType() {
        return "Money Market";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.045;
    }

}