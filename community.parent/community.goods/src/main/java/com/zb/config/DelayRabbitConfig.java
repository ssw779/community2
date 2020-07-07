package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayRabbitConfig {

    //死信的信息
    //队列
    public static final String ORDER_DELAY_QUEUE = "community.goods.delay.queue";
    //交换机
    public static final String ORDER_DELAY_EXCHANGE = "community.goods.delay.exchange";
    //routingkey
    public static final String ORDER_DELAY_ROUTING_KEY = "goods_delay";

    //非死信的信息
    //队列
    public static final String ORDER_QUEUE_NAME = "community.goods.queue";
    //交换机
    public static final String ORDER_EXCHANGE_NAME = "community.goods.exchange";
    //routingkey
    public static final String ORDER_ROUTING_KEY = "goods";

    //在普通队列上添加死信配置
    @Bean
    public Queue createDirectQueue() {
        Map<String, Object> param = new HashMap<String, Object>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        param.put("x-dead-letter-exchange", ORDER_DELAY_EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        param.put("x-dead-letter-routing-key", ORDER_DELAY_ROUTING_KEY);
        return new Queue(ORDER_QUEUE_NAME, true, false, false, param);
    }

    @Bean
    public DirectExchange createDirectExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding dlxBind() {
        return BindingBuilder.bind(createDirectQueue()).to(createDirectExchange()).with(ORDER_ROUTING_KEY);
    }

    @Bean
    public Queue createQueue() {
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, null);
    }

    @Bean
    public TopicExchange createTopicExchange() {
        return new TopicExchange(ORDER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding orderBind() {
        return BindingBuilder.bind(createQueue()).to(createTopicExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }
}
