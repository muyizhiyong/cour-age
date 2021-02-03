package com.muyi.courage.push.listener;

import com.muyi.courage.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @author 杨志勇
 * @date 2021-02-02 15:51
 */

@Component
@Slf4j
public class RedisMessageListener implements MessageListener {
    /**
     * websocket客户端连接会话对象
     */
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody()).replace("\"", "");
        if(session !=null  && session.isOpen()){
            try {
                session.getBasicRemote().sendText(msg);
            }catch (IOException e){
                log.error("RedisSubListener消息订阅监听异常：" + e.getMessage());
            }
        }

    }
}
