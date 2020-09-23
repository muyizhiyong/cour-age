package com.muyi.courage.rabbitMq.other2boot;

import com.muyi.courage.handler.MsgHandler;
import com.muyi.courage.rabbitMq.util.MQDataModule;
import com.muyi.courage.rabbitMq.util.MQDocument;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Component
public class RabbitMqMsgConsumer {

    @Resource
    private MsgHandler msgHandler;


    @RabbitListener(queues = "#{'${rabbitmq.queue-name.other2boot}'.split(',')}")
    public String process(Message message, Channel channel) {
        try {
            String dataModuleJson = new String(message.getBody());
            log.info("[RabbitMqMsgConsumer] received message:{}", dataModuleJson);

            // TODO: 2020-09-22
            // 解析处理消息内容
            // resMsg = "{'a':'1','b':2}"  参数是：map 或者 dto
            // resMsg = "username"    参数是：string
            // resMsg = "[1,2,3]"     参数是：list
            String reqType ="getMsgByType1";
            Object resMsg = msgHandler.doHandler(reqType,new Object[]{dataModuleJson});



            //业务信息包装一层MQDocument返回
            MQDocument mqDocument = new MQDocument();
            MQDataModule.setNodeObjValue("MQDataModule.BussinessInfo",resMsg,mqDocument);
            MQDataModule.setNodeObjValue("MQDataModule.System.ChannelID",channel.getChannelNumber(),mqDocument);
            MQDataModule.setNodeObjValue("MQDataModule.System.Reveiver","muyi",mqDocument);

            String consumerResult =MQDataModule.convertDOMToText(mqDocument);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("[RabbitMqMsgConsumer] message consume succeed return result is:{}", consumerResult);
            return consumerResult;
        }catch (Exception e) {
            log.error("[RabbitMqMsgConsumer] message consume failed!", e);
            try {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException ex) {
                log.error("[RabbitMqMsgConsumer] message rejected failed!", ex);
                return null;
            }
            return null;
        }
    }
}
