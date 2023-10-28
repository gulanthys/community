package com.yanty.friends.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
