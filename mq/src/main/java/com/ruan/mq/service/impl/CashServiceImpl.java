package com.ruan.mq.service.impl;

import com.ruan.mq.constant.TransactionEnum;
import com.ruan.mq.service.ITransactionService;
import org.springframework.stereotype.Service;

@Service
public class CashServiceImpl implements ITransactionService {

    @Override
    public TransactionEnum getTransaction() {
        return TransactionEnum.CASH;
    }

    @Override
    public String transaction(String id) {
        return id + "提现成功";
    }
}
