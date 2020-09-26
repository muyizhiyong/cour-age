package com.muyi.courage.rabbitMq.boot2other.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "rabbitmq.queue-name")
public class RabbitMqConfig<Rabbit> {

    private String exchange;
    private Map<String, String> boot2other;
    private List<String> other2boot;

    @Value("${rabbitmq.exchange}")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //autoStartup 必须要设为 true ，否则Spring容器不会加载RabbitAdmin类
        rabbitAdmin.setAutoStartup(true);
        //声明交换机
        rabbitAdmin.declareExchange(exchange());
        //声明2个方向的队列
        boot2otherQueue().forEach(rabbitAdmin::declareQueue);
        other2BootQueue().forEach(rabbitAdmin::declareQueue);
        //声明队列与交换机的绑定
        bindingBoot2OtherMessage().forEach(rabbitAdmin::declareBinding);
        bindingOther2BootMessage().forEach(rabbitAdmin::declareBinding);
        return rabbitAdmin;
    }

    public List<Queue> boot2otherQueue() {
        List<Queue> queueList = new ArrayList<>();
        boot2other.values().forEach(s -> queueList.add(new Queue(s)));
        return queueList;
    }

    public List<Queue> other2BootQueue() {
        List<Queue> queueList = new ArrayList<>();
        other2boot.forEach(s -> queueList.add(new Queue(s)));
        return queueList;
    }

    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    List<Binding> bindingBoot2OtherMessage() {
        List<Binding> bindingList = new ArrayList<>();
        List<Queue> queueList = boot2otherQueue();
        queueList.forEach(queue -> bindingList.add(BindingBuilder.bind(queue).to(exchange()).with(queue.getActualName())));
        return bindingList;
    }

    List<Binding> bindingOther2BootMessage() {
        List<Binding> bindingList = new ArrayList<>();
        List<Queue> queueList = other2BootQueue();
        queueList.forEach(queue -> bindingList.add(BindingBuilder.bind(queue).to(exchange()).with(queue.getActualName())));
        return bindingList;
    }


    public Map<String, String> getBoot2other() {
        return boot2other;
    }

    public void setBoot2other(Map<String, String> boot2other) {
        this.boot2other = boot2other;
    }

    public List<String> getOther2boot() {
        return other2boot;
    }

    public void setOther2boot(List<String> other2boot) {
        this.other2boot = other2boot;
    }

    public String getExchange() {
        return exchange;
    }
}
