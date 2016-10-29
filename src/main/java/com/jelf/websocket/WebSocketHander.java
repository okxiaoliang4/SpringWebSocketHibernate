package com.jelf.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.jelf.entity.Message;

public class WebSocketHander implements WebSocketHandler {
	private static final Logger logger = Logger
			.getLogger(WebSocketHander.class);

	private static final ArrayList<WebSocketSession> users = new ArrayList<>();

	// 初次链接成功执行
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		logger.debug("链接成功......");
		users.add(session);
		// String userId = (String) session.getAttributes().get(
		// "WEBSOCKET_USERID");
		// if (userId != null) {
		// // 查询未读消息
		// int count = 5;
		// session.sendMessage(new TextMessage(count + ""));
		// }
	}

	// 接受消息处理消息
	@Override
	public void handleMessage(WebSocketSession webSocketSession,
			WebSocketMessage<?> webSocketMessage) throws Exception {
		Message message = JSON.parseObject(webSocketMessage.getPayload()
				.toString(), Message.class);
		message.setUserId(webSocketSession.getAttributes()
				.get("WEBSOCKET_USERID").toString());
		message.setDate(new Date());
		// TODO 判断是否是好友，不是好友不能发
		sendMessageToUser(message.getUserId(),
				new TextMessage(message.toString()));
		
		sendMessageToUser(message.getToUserId(),
				new TextMessage(message.toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession webSocketSession,
			Throwable throwable) throws Exception {
		if (webSocketSession.isOpen()) {
			webSocketSession.close();
		}
		logger.debug("链接出错，关闭链接......");
		users.remove(webSocketSession);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession,
			CloseStatus closeStatus) throws Exception {
		logger.debug("链接关闭......" + closeStatus.toString());
		users.remove(webSocketSession);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给某个用户发送消息
	 *
	 * @param userId
	 * @param message
	 */
	public void sendMessageToUser(String userId, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get("WEBSOCKET_USERID").equals(userId)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}