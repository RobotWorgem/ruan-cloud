package com.ruan.mq.service;

import com.ruan.mq.constant.TransactionEnum;
import com.ruan.mq.service.impl.TransactionService;
import org.springframework.boot.CommandLineRunner;

/**
 * @author XY
 */
public interface ITransactionService extends CommandLineRunner {

    /**
     * 获取交易类型
     *
     * @return
     */
    TransactionEnum getTransaction();

    /**
     * 交易方法
     *
     * @param id
     * @return
     */
    String transaction(String id);

    /**
     * 注册交易类型
     *
     * @param args
     * @throws Exception
     */
    @Override
    default void run(String... args) throws Exception {
        TransactionService.register(this);
    }
}
