package com.muyi.courage.push.websocket;

import com.alibaba.fastjson.JSONObject;
import com.muyi.courage.common.util.RedisUtil;
import com.muyi.courage.common.util.SpringContextUtil;
import com.muyi.courage.common.util.SpringUtils;
import com.muyi.courage.push.config.WebSocketHttpSessionConfig;
import com.muyi.courage.push.dto.UserDTO;

import com.muyi.courage.push.listener.RedisMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Slf4j
@ServerEndpoint(value = "/push-module/websocket", configurator = WebSocketHttpSessionConfig.class)
@Component
public class WebSocketServer {

	/**
	 * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	 */
	private static int onlineCount = 0;

	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象
	 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;

    /**
     * 用户连接session
     */
    public static final ConcurrentHashMap<String, WebSocketSession> USER_SESSION_MAP = new ConcurrentHashMap<>();;

    /**
     * 用PushRedisConfig注入的类
     */
    private RedisMessageListenerContainer container = SpringUtils.getBean("redisMessageListenerContainer");

    /**
     * 自定义的消息发送器
     */
    private RedisMessageListener listener;


    /**
	 * 接收username
	 */
	private String userno;


	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
		Principal userPrincipal = session.getUserPrincipal();
		if (userPrincipal instanceof UserDTO) {
			// 获取权限框架验证过的的身份信息，框架自动通过请求头token验证
			log.debug("[userPrincipal instanceof UserDTO]: true");
			userno = ((UserDTO) userPrincipal).getUserNo();
			openSuccess(session);
//			return;

		} else if (requestParameterMap.size() != 0) {
			List<String> tokenList = requestParameterMap.get("token");
			if (tokenList != null) {
				String token = tokenList.get(0);
				log.debug("[requestParameterMap token ]: {}", token);
//				String authToken = JwtHelper.decode(token).getClaims();
//				JSONObject jsonObject = JSONObject.parseObject(authToken);
//				userno = jsonObject.getString("user_name");
				openSuccess(session);
				return;
			}
		}

		try {
			log.info("[session close because without token]");
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openSuccess(Session session) {
		webSocketSet.add(this);
		this.session = session;
		addOnlineCount();
		log.info("新会话加入，当前在线人数为" + getOnlineCount());
		try {
			sendMessage("连接成功");
            listener = new RedisMessageListener();
            //放入session
            listener.setSession(session);
            container.addMessageListener(listener, new PatternTopic("liveBroadcastTopic"));

        } catch (IOException e) {
			log.error("websocket IO异常", e);
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		// 从set中删除
		webSocketSet.remove(this);
		// 在线数减1
		subOnlineCount();
        container.removeMessageListener(listener);
		log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 * @param session 与客户端连接的会话
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
            message = "Server received MSG:" + message;
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发生错误
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo2User(String message, String userno) {
		log.info("推送消息到[{}]，推送内容: {}", userno == null ? "all" : userno, message);
		for (WebSocketServer item : webSocketSet) {
			log.info("WebSocketServer item : {}", item.userno);
			try {
				if (userno == null || item.userno.equals(userno)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				log.error("sendInfo exception ", e);
			}
		}


	}


	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}
