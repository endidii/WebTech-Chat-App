package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.dto.ChatMessage;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@CrossOrigin(origins = "*")
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/chat.sendMessage/{channelId}")
    @SendTo("/topic/channel/{channelId}")
    public ChatMessage broadcastMessage(@Payload ChatMessage chatMessage, @DestinationVariable String channelId) {
        logger.info("Received message in channel {}: {}", channelId, chatMessage);
        // The message is automatically broadcast to all subscribers of '/topic/channel/{channelId}'
        return chatMessage;
    }

    // Handle WebSocket connection event
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    // Handle WebSocket disconnection event
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("A connection was closed");
    }
}
