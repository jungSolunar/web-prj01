package com.atto.server.ws;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "ws")
@Validated
public class WebSocketConfigurationProperties {

    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean hasUrl() {
        return (null != url);
    }

    @Override
    public String toString() {
        return new String("ws.url=" + url);
    }
}
