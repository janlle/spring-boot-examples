package com.andy.design.behavior.duty;


/**
 * <p> 告警流规则生成
 *
 * @author Leone
 * @since: 2018-08-22
 **/
public class StreamGenerateHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        System.out.println("Generate stream success.");
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll Generate stream.");
    }

}