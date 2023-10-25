package com.yanty.friends.config;

import com.yanty.friends.ws.DefaultWebSocketHandler;
import com.yanty.friends.ws.WebSocketInterceptor;
import com.yanty.friends.ws.service.WebSocketService;
import com.yanty.friends.ws.service.impl.WebSocketServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class WebsocketConfig{

    @Bean
    //ServerEndpointExporter，自动注册使用@ServerEndpoint
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
