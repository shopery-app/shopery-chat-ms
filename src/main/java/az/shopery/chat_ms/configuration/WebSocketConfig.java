package az.shopery.chat_ms.configuration;

import az.shopery.chat_ms.utils.websocket.EmailHandshakeInterceptor;
import az.shopery.chat_ms.utils.websocket.WebSocketUserHandshakeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final EmailHandshakeInterceptor emailHandshakeInterceptor;
    private final WebSocketUserHandshakeHandler webSocketUserHandshakeHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .addInterceptors(emailHandshakeInterceptor)
                .setHandshakeHandler(webSocketUserHandshakeHandler)
                .setAllowedOriginPatterns("*");
    }
}
