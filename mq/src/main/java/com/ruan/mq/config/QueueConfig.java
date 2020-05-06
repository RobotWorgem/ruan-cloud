package com.ruan.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class QueueConfig {


    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    /**
     * 发送到该队列的message立刻过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_0S_TTL_NAME = "delay_queue_0s_ttl";
    /**
     * 发送到该队列的message会在15S后过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_15S_TTL_NAME = "delay_queue_15s_ttl";

    /**
     * 发送到该队列的message会在30S后过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_30S_TTL_NAME = "delay_queue_30s_ttl";

    /**
     * 发送到该队列的message会在60S后过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_60S_TTL_NAME = "delay_queue_60s_ttl";

    /**
     * 发送到该队列的message会在2MIN后过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_5MIN_TTL_NAME = "delay_queue_2min_ttl";

    /**
     * 发送到该队列的message会在3MIN后过期进入到delay_process_queue
     */
    static final String DELAY_QUEUE_15MIN_TTL_NAME = "delay_queue_3min_ttl";

    /**
     * 延迟消息的真正处理队列
     */
    public static final String DELAY_QUEUE_PROCESS_NAME = "delay_queue_process";

    /**
     * 商户通知消息广播交换机名称
     */
    public static final String MERCHANT_NOTICE_EXCHANGE_NAME = "merchant_notice_exchange";

    /**
     * 声明死信队列的交换机名称参数 KEY，固定值
     */
    private static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    /**
     * 死信队列接收交换机名称
     */
    private static final String DELAY_DLX_NAME = "delay_dlx";

    /**
     * 声明死信队列交换机绑定的路由键参数 KEY，固定值
     */
    private static final String DEAD_LETTER_ROUTINGKEY = "x-dead-letter-routing-key";

    /**
     * 声明死信队列的 TTL 属性参数 KEY，固定值
     */
    private static final String EXPIRATION = "x-message-ttl";

    /**
     * 时间单位：秒
     */
    private static final int SECOND = 1000;

    /**
     * 时间单位：分
     */
    private static final int MINUTE = 60 * 1000;
    /**
     * 支付订单见证充值查询交换机
     */
//    public static final String PAY_ORDER_RECHARGE_EXCHANGE_NAME = "channel_order_recharge_exchange";
    /**
     * 支付订单见证充值查询队列
     */
//    public static final String PAY_ORDER_RECHARGE_QUEUE_PROCESS_NAME = "recharge_query_queue_process";
    /**
     * 见证充值查询交换机和队列绑定键
     */
//    public static final String PAY_ORDER_RECHARGE_ROUTINGKEY = "order-recharge-routing-key";


    /**
     * 设置rabbitTemplate为多例
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }


    /**
     * 商户通知消息的广播交换机，背后绑定了 11 次通知机制的死信队列
     *
     * @return
     */
    @Bean
    FanoutExchange merchantNoticeExchange() {
        return new FanoutExchange(MERCHANT_NOTICE_EXCHANGE_NAME);
    }

    /**
     * 私信队列转发交换机，同时绑定真正的消费队列
     *
     * @return
     */
    @Bean
    TopicExchange delayDlx() {
        return new TopicExchange(DELAY_DLX_NAME);
    }


    /**
     * 第一次通知机制：0 秒过期，立即消费
     *
     * @return
     */
    @Bean
    Queue delayQueue0sTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_0S_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.0s")
                .withArgument(EXPIRATION, 0)
                .build();
    }


    /**
     * 第二次通知机制：15 秒过期，距离上次通知 15 秒间隔
     *
     * @return
     */
    @Bean
    Queue delayQueue15sTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_15S_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.15s")
                .withArgument(EXPIRATION, 15 * SECOND)
                .build();
    }

    /**
     * 第三次通知机制：30 秒过期，距离上次通知 15 秒间隔
     *
     * @return
     */
    @Bean
    Queue delayQueue30sTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_30S_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.30s")
                .withArgument(EXPIRATION, 30 * SECOND)
                .build();
    }

    /**
     * 第四次通知机制：60 秒过期，距离上次通知 30 秒间隔
     *
     * @return
     */
    @Bean
    Queue delayQueue60sTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_60S_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.60s")
                .withArgument(EXPIRATION, MINUTE)
                .build();
    }

    /**
     * 第五次通知机制：2 分钟过期，距离上次通知 1 分钟间隔
     *
     * @return
     */
    @Bean
    Queue delayQueue2MinTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_5MIN_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.2min")
                .withArgument(EXPIRATION, 2 * MINUTE)
                .build();
    }

    /**
     * 第六次通知机制：3 分钟过期，距离上次通知 1 分钟间隔
     *
     * @return
     */
    @Bean
    Queue delayQueue3MinTtl() {
        return QueueBuilder
                .durable(DELAY_QUEUE_15MIN_TTL_NAME)
                .withArgument(DEAD_LETTER_EXCHANGE, DELAY_DLX_NAME)
                .withArgument(DEAD_LETTER_ROUTINGKEY, "delay.3min")
                .withArgument(EXPIRATION, 3 * MINUTE)
                .build();
    }

    @Bean
    Queue delayQueueProcess() {
        return QueueBuilder.durable(DELAY_QUEUE_PROCESS_NAME).build();
    }

    @Bean
    Binding queueTtl0sBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue0sTtl) {
        return BindingBuilder.bind(delayQueue0sTtl).to(merchantNoticeExchange);
    }

    @Bean
    Binding queueTtl15sBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue15sTtl) {
        return BindingBuilder.bind(delayQueue15sTtl).to(merchantNoticeExchange);
    }

    @Bean
    Binding queueTtl30sBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue30sTtl) {
        return BindingBuilder.bind(delayQueue30sTtl).to(merchantNoticeExchange);
    }

    @Bean
    Binding queueTtl60sBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue60sTtl) {
        return BindingBuilder.bind(delayQueue60sTtl).to(merchantNoticeExchange);
    }

    @Bean
    Binding queueTtl5MinBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue2MinTtl) {
        return BindingBuilder.bind(delayQueue2MinTtl).to(merchantNoticeExchange);
    }

    @Bean
    Binding queueTtl15MinBinding(FanoutExchange merchantNoticeExchange, Queue delayQueue3MinTtl) {
        return BindingBuilder.bind(delayQueue3MinTtl).to(merchantNoticeExchange);
    }

    /**
     * 将DLX绑定到实际消费队列
     */
    @Bean
    Binding dlxBinding(TopicExchange delayDlx, Queue delayQueueProcess) {
        return BindingBuilder.bind(delayQueueProcess).to(delayDlx).with("delay.#");
    }


}