package com.andy.design.behavior.duty;


import org.springframework.util.StringUtils;

/**
 * <p> 阈值解析
 *
 * @author Leone
 * @since: 2018-08-22
 **/
public class ThresholdParseHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        if (StringUtils.isEmpty(rule.getThreshold())) {
            throw new Exception("Threshold is empty.");
        }
        System.out.println("Parse threshold success. Threshold is " + rule.getThreshold());
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll parse threshold. Threshold is " + rule.getThreshold());
    }

}