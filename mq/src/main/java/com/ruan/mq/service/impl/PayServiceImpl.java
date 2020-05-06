package com.ruan.mq.service.impl;

import com.ruan.mq.constant.TransactionEnum;
import com.ruan.mq.service.ITransactionService;
import org.springframework.stereotype.Service;

/**
 * @author XY
 */
@Service
public class PayServiceImpl implements ITransactionService {
    @Override
    public TransactionEnum getTransaction() {
        return TransactionEnum.PAY;
    }

    @Override
    public String transaction(String id) {
        return id + "支付成功";
    }


}
