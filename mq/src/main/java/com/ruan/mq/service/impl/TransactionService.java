package com.ruan.mq.service.impl;

import com.ruan.mq.pojo.Trans;
import com.ruan.mq.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂+策略模式
 */
@Slf4j
public class TransactionService {
    static Map<String, ITransactionService> transactionServiceMap = new ConcurrentHashMap<>();

    private static List<LocalDateTime> times;

    public static List<LocalDateTime> getTimes(){
        if (null == times){
            synchronized (TransactionService.class){
                if (null == times){
                    times = new ArrayList<>();
                }
            }
        }
        return times;
    }

    public static void setTimes(List<LocalDateTime> times) {
        TransactionService.times = times;
    }

    public static void register(ITransactionService transactionService) {
        log.info("register type: {}, desc: {}", transactionService.getTransaction().getType(), transactionService.getTransaction().getDesc());
        transactionServiceMap.put(transactionService.getTransaction().getType(), transactionService);
    }

    public static String transactions(Trans trans) {
        ITransactionService transactionService = transactionServiceMap.get(trans.getType());
        if (null == transactionService) {
            return "暂不支持次业务";
        }
        return transactionService.transaction(trans.getId());

    }
}
