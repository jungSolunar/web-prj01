package com.atto.server.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    private WebSocketSessionManager wsSessionManager = WebSocketSessionManager.getInstance();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("[WEBSOCKET] connected ws: " + session.getId());
        wsSessionManager.addWebSocketSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("[WEBSOCKET] disconnected ws: " + session.getId());
        wsSessionManager.removeWebSocketSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("[WEBSOCKET] Receive a new message:  client=" + session.getId() + ",message="  + message.getPayload());
    }
}
