package com.ruan.mq.constant;

/**
 * @author XY
 */

public enum TransactionEnum {

    /**
     * 支付
     */
    PAY("PAY", "支付"),
    /**
     * 退款
     */
    REFUND("REFUND", "退款"),
    /**
     * 提现
     */
    CASH("CASH", "提现");

    String type;
    String desc;

    TransactionEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
