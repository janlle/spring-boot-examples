package com.andy.pay.common.enums;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 20:14
 **/
public enum OrderStatusEnum {

    CREATE(0),PAY(1),DELIVER(2),DELIVERY(3),FINISH(4),CLEAN(5),CLOSE(6),DELETE(7);

    private int status;

    OrderStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
