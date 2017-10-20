package com.atto.server.ws;

import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketSendService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private WebSocketSessionManager wsSessionManager = WebSocketSessionManager.getInstance();

    public void sendMessageToAllClients(String message) {
        log.debug("[WEBSOCKET] sendMessageToAllClients: " + message);

        Iterator<WebSocketSession> wsSessionIterator = wsSessionManager.getIterator();
        while (wsSessionIterator.hasNext()) {
            WebSocketSession wsSession = wsSessionIterator.next();
            sendMessageToClient(wsSession, message);
        }
    }

    public void sendMessageToClient(WebSocketSession wsSession, String message) {
        log.debug("[WEBSOCKET] sendMessageToClient: id=" + wsSession.getId() + ", " + message);

        TextMessage msg = new TextMessage(message);

        try {
            wsSession.sendMessage(msg);
        } catch (IOException ioe) {
            log.error("[ERROR] failed to send message to client[ " + wsSession.getId() + "] via websocket: " + ioe.getLocalizedMessage());
            ioe.printStackTrace();
        }
    }

}
