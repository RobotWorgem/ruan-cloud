package com.ruan.mq.service;

import com.ruan.mq.constant.TransactionEnum;
import com.ruan.mq.service.impl.TransactionService;
import org.springframework.boot.CommandLineRunner;

public interface ITransactionService extends CommandLineRunner {

    TransactionEnum getTransaction();

    String transaction(String id);

    @Override
    default void run(String... args) throws Exception {
        TransactionService.register(this);
    }
}
