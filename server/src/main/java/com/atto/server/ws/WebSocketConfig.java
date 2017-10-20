package com.atto.server.ws;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableConfigurationProperties(WebSocketConfigurationProperties.class)
public class WebSocketConfig implements WebSocketConfigurer {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final WebSocketConfigurationProperties wsProperties;

    private String url;

    @Autowired
    public WebSocketConfig(WebSocketConfigurationProperties wsProperties) {
        this.wsProperties = wsProperties;
    }

    @PostConstruct
    public void checkProperties() {
        if (wsProperties.hasUrl()) {
            url = wsProperties.getUrl();
        } else {
            throw new RuntimeException("[WEBSOCKET] URL is missing!!!");
        }

        log.info("[WEBSOCKET] url=" + url);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(), url).withSockJS();
    }
}
