package com.team08.csci205_final_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSocket and message brokering in the Spring application.
 * This class configures WebSocket message broker options and endpoints.
 * The class configures STOMP endpoints and sets up a simple message broker
 * to carry the payload of messages back and forth.
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Registers STOMP endpoints for WebSocket communication.
     * This method sets up the endpoint that clients will use to connect to the WebSocket.
     * SockJS is enabled to allow for fallback options in browsers that do not support WebSocket.
     *
     * @param registry the STOMP endpoint registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }

    /**
     * Configures the message broker for handling messages in WebSocket communication.
     * This method sets up the application prefix for filtering destinations targeting
     * application annotated methods and configures the prefixes for the built-in simple
     * broker for handling messages to clients.
     *
     * @param registry the message broker registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue", "/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }
}
