package com.atto.server.ws;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionManager {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static final WebSocketSessionManager wsSessionManager = new WebSocketSessionManager();

    private Map<String, WebSocketSession> wsSessionMap = new HashMap<String, WebSocketSession>();

    private WebSocketSessionManager () {
    }

    public static WebSocketSessionManager getInstance() {
        return wsSessionManager;
    }

    public synchronized void addWebSocketSession(WebSocketSession client) {
        log.debug("[WEBSOCKET] addWebSocketSession: " + client.getId() + ", " + client.getUri().toString());
        wsSessionMap.put(client.getId(), client);
    }
    public synchronized void removeWebSocketSession(WebSocketSession removedClient) {
        log.debug("[WEBSOCKET] removeWebSocketSession: " + removedClient.getId() + ", " + removedClient.getUri().toString());
        wsSessionMap.remove(removedClient.getId());
    }

    public Iterator<WebSocketSession> getIterator () {
        return wsSessionMap.values().iterator();
    }

}
