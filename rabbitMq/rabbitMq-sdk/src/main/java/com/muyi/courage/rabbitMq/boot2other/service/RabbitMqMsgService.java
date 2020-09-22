package com.muyi.courage.rabbitMq.boot2other.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muyi.courage.rabbitMq.boot2other.RabbitMqMsgProducer;
import com.muyi.courage.rabbitMq.boot2other.config.RabbitMqConfig;
import com.muyi.courage.rabbitMq.util.MQDataModule;
import com.muyi.courage.rabbitMq.util.MQDocument;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Data
@Service
public class RabbitMqMsgService {

    final RabbitMqMsgProducer rabbitMqMsgProducer;

    @Value("${rabbitmq.data.boot2other.timeout}")
    private String timeout;

    @Resource
    private RabbitMqConfig rabbitMqConfig;

    public RabbitMqMsgService(RabbitMqMsgProducer rabbitMqMsgProducer){
        this.rabbitMqMsgProducer = rabbitMqMsgProducer;
    }

    public <T> T sendInfo( String msg, Class<T> returnType, String channelID) {
        MQDocument mqDocument = new MQDocument();

        Map<String, String> config = rabbitMqConfig.getBoot2other();
        String routingKey = config.get(channelID);
        if (routingKey == null) {
            log.error("can't find queue for channelID, please check application.yml");
            return null;
        }
        //业务信息包装一层MQDocument发送
        MQDataModule.setNodeObjValue("MQDataModule.BussinessInfo",msg,mqDocument);
        MQDataModule.setNodeObjValue("MQDataModule.System.ChannelID",channelID,mqDocument);
        MQDataModule.setNodeObjValue("MQDataModule.System.TimeOut",timeout,mqDocument);

        String reqMsg = MQDataModule.convertDOMToText(mqDocument);

        JSONObject response = rabbitMqMsgProducer.sendInfo(reqMsg, Long.parseLong(timeout), routingKey);
        if (response == null) {
            log.error("response is null!");
            return null;
        }
        String returnJson = response.toJSONString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(returnJson, returnType);
        }catch (IOException e) {
            log.error("can't convert json:{} to {}", returnJson, returnType.getName());
            return null;
        }

    }
}
