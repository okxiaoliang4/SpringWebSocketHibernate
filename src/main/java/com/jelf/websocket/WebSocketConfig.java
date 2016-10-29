package com.jelf.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
// 开启websocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new WebSocketHander(), "/ws.do").addInterceptors(
				new HandshakeInterceptor()); // 支持websocket 的访问链接
		registry.addHandler(new WebSocketHander(), "/sockjs/ws.do")
				.addInterceptors(new HandshakeInterceptor()).withSockJS(); // 不支持websocket的访问链接
	}

}