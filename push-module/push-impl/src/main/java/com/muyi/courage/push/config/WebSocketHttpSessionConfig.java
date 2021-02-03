package com.muyi.courage.push.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;


public class WebSocketHttpSessionConfig extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec,
								HandshakeRequest request,
								HandshakeResponse response) {
		sec.getUserProperties().put("headers", request.getHeaders());
//		sec.getUserProperties().put("parameterMap", request.getParameterMap());
//		sec.getUserProperties().put("userPrincipal", request.getUserPrincipal());
	}


}
