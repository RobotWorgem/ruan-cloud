package com.ruan.mq.producer;

import com.ruan.mq.config.QueueConfig;
import com.ruan.mq.pojo.Trans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
@Slf4j
public class MerNoticeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 回调函数: confirm确认
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        log.info("correlationData:->{},ack->{}", correlationData,ack);
        String messageId = correlationData.getId();
        if (ack) {
            // TODO 如果confirm返回成功 则进行更新BrokerMessageLog表消费状态为成功
            log.info("商户通知消息确认投递成功,当前消息ID为:{}", messageId);
        } else {
            // TODO 失败则进行具体的后续操作:判断是否超过最大允许重试次数，未超过重试，已超过风险通知，并更新相关信息
            log.error("商户通知消息投递失败，当前消息ID为:{},失败原因:{}", messageId, cause);
        }
    };

    /**
     * 回调函数: return返回
     */
    private final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        //TODO 消息发送失败，风险事件告警
        Trans trans = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()));
            trans = (Trans) ois.readObject();
        } catch (IOException e) {
            log.error("反序列化消息对象异常",e);
        } catch (ClassNotFoundException e) {
            log.error("消息对象类不存在",e);
        }
        log.error("return messageData:{}, exchange:{}, routingKey:{}, replyCode:{}, replyText:{}" ,trans, exchange ,routingKey , replyCode ,replyText);
    };

    public void sendNoticeData(Trans trans) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData(trans.getId().concat(trans.getType()));
        rabbitTemplate.convertAndSend(QueueConfig.MERCHANT_NOTICE_EXCHANGE_NAME,null,trans,correlationData);
    }
}
