package com.ruan.mq.consunmer;

import com.rabbitmq.client.Channel;
import com.ruan.mq.config.QueueConfig;
import com.ruan.mq.pojo.Trans;
import com.ruan.mq.service.impl.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MerNoticeReceiver {

    @RabbitListener(queues = QueueConfig.DELAY_QUEUE_PROCESS_NAME)
    @RabbitHandler
    public void receiver(@Payload Trans trans,
                         Channel channel,
                         @Headers Map<String, Object> headers) throws IOException {

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("received message from broker,notice-message -> {},properties -> {}", trans, headers);
        String msg = TransactionService.transactions(trans);
        log.info("msg:{}", msg);
        List<LocalDateTime> times = TransactionService.getTimes();
        LocalDateTime currentTime = LocalDateTime.now();
        if (times.size() == 0) {
            times.add(currentTime);
            log.info("第1次调用，当前时间：{}", currentTime);
        } else {
            LocalDateTime lastTime = times.get(times.size() - 1);
            log.info("第{}次调用,当前时间{}，间隔时间：{}", times.size(), currentTime, Duration.between(lastTime, currentTime));
        }
        times.add(currentTime);
        log.info("The current message is successfully processed,tag -> {},msg_id -> {},trade_type -> {}", deliveryTag, trans.getId(), trans.getType());
//        channel.basicAck(deliveryTag, false);

    }
}
