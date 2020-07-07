package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    public static final String myexchange="community-goods-exchange";
    public static final String myqueue ="community-goods-queue";

    @Bean(myexchange)
    public Exchange createExchange(){
        return ExchangeBuilder.topicExchange(myexchange).durable(true).build();
    }

    @Bean(myqueue)
    public Queue createQueue(){
        Queue queue =new Queue(myqueue);
        return queue;
    }

    @Bean
    public Binding bindingGoods(@Qualifier(myexchange) Exchange exchange , @Qualifier(myqueue) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("qg.#.goods.#").noargs();
    }
}
