package com.ruan.mq.constant;

public enum TransactionEnum {

    PAY("PAY", "支付"),
    REFUND("REFUND", "退款"),
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
