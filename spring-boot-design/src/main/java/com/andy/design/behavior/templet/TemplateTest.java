package com.andy.design.behavior.templet;

/**
 * @author: Leone
 * @since: 2018-08-02
 **/
public class TemplateTest {

    public static void main(String[] args) {
        Account account = new MoneyMarketAccount();
        System.out.println("货币市场账号的利息数额为：" + account.calculateInterest());
        account = new CDAccount();
        System.out.println("定期账号的利息数额为：" + account.calculateInterest());
    }

}
