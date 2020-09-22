package com.muyi.courage.rabbitMq.boot2other;

import com.alibaba.fastjson.JSONObject;
import com.muyi.courage.rabbitMq.boot2other.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
@Component
public class RabbitMqMsgProducer {

    @Resource
    private RabbitMqConfig rabbitMqConfig;

    @Resource
    private RabbitTemplate rabbitTemplate;

    public RabbitMqMsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public JSONObject sendInfo(String message, long timeOut, String routingKey) {
        String exchange = rabbitMqConfig.getExchange();
        log.info("[RabbitMqMsgProducer] , queue: {}, send message: {}", routingKey, message);
        rabbitTemplate.setReceiveTimeout(timeOut);
        rabbitTemplate.setReplyTimeout(timeOut);
        Object response = rabbitTemplate.convertSendAndReceive(exchange, routingKey, message);
        log.info("[RabbitMqMsgProducer] received message: {}", response);
        return JSONObject.parseObject((String) response);
    }

}
