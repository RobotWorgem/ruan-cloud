package com.ruan.mq.service.impl;

import com.ruan.mq.constant.TransactionEnum;
import com.ruan.mq.service.ITransactionService;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements ITransactionService {
    @Override
    public TransactionEnum getTransaction() {
        return TransactionEnum.REFUND;
    }

    @Override
    public String transaction(String id) {
        return id + "退款成功";
    }
}
