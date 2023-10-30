package com.yanty.friends.config;

import com.yanty.friends.ws.DefaultWebSocketHandler;
import com.yanty.friends.ws.WebSocketInterceptor;
import com.yanty.friends.ws.service.WebSocketService;
import com.yanty.friends.ws.service.impl.WebSocketServiceImpl;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 注入到springboot ioc容器
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Bean
    public DefaultWebSocketHandler defaultWebSocketHandler() {
        return new DefaultWebSocketHandler();
    }

    @Bean
    public WebSocketService webSocket() {
        return new WebSocketServiceImpl();
    }

    @Bean
    public WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }

//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

    /**
     * 接口注册
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(defaultWebSocketHandler(), "ws/message")
                    .addInterceptors(webSocketInterceptor())
                    .setAllowedOrigins("*");
    }
}
