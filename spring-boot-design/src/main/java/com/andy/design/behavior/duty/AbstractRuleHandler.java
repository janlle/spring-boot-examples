package com.andy.design.behavior.duty;

/**
 * <p> 告警规则责任链处理节点抽象类
 *
 * @author Leone
 * @since 2018-08-22
 **/
public abstract class AbstractRuleHandler {
    // 上一个处理器
    private AbstractRuleHandler preHandler;

    // 下一个处理器
    private AbstractRuleHandler nextHandler;

    /**
     * 业务执行
     *
     * @param rule
     * @author coshaho
     */
    public void doHandle(AlarmRule rule) {
        try {
            doHandleReal(rule);
        } catch (Exception e) {
            // 业务代码执行失败主动回滚
            rollBack(rule);
            return;

        }

        // 业务代码执行成功主动调用下一个处理器处理
        if (null != nextHandler) {
            nextHandler.doHandle(rule);
        }
    }

    /**
     * 事务回滚
     *
     * @param rule
     * @author coshaho
     */
    public void rollBack(AlarmRule rule) {
        rollBackReal(rule);
        // 本处理器业务回滚完成，主动调用前一个处理器业务回滚
        if (null != preHandler) {
            preHandler.rollBack(rule);
        }
    }

    /**
     * 每个处理器特有的业务处理方法
     *
     * @param rule
     * @throws Exception
     * @author coshaho
     */
    public abstract void doHandleReal(AlarmRule rule) throws Exception;

    /**
     * 每个处理器特有的业务回滚方法
     *
     * @param rule
     * @author coshaho
     */
    public abstract void rollBackReal(AlarmRule rule);

    private AbstractRuleHandler setPreHandler(AbstractRuleHandler preHandler) {
        this.preHandler = preHandler;
        return preHandler;
    }

    public AbstractRuleHandler setNextHandler(AbstractRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
        nextHandler.setPreHandler(this);
        return nextHandler;
    }

}