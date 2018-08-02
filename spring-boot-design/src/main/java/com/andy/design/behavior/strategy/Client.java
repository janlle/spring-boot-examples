package com.andy.design.behavior.strategy;

public class Client {

    public static void main(String[] args) {
        //选择并创建需要使用的策略对象
        MemberStrategy a = new AdvancedMemberStrategy();
        MemberStrategy b = new IntermediateMemberStrategy();
        MemberStrategy c = new PrimaryMemberStrategy();
        //创建环境
        Price price = new Price(c);
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }

}