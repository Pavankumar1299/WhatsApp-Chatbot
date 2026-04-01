package com.example.whatsappchatbot; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping
    public Map<String, String> handleWhatsAppMessage(@RequestBody Map<String, String> payload) {
        String incomingMessage = payload.getOrDefault("message", "").trim().toLowerCase();
        String senderId = payload.getOrDefault("sender", "Unknown");

        logger.info("Incoming WhatsApp Message - Sender: {}, Message: '{}'", senderId, incomingMessage);

        String replyText;
        switch (incomingMessage) {
            case "hi":
                replyText = "Hello! Welcome to our simulated WhatsApp bot.";
                break;
            case "bye":
                replyText = "Goodbye! Have a great day.";
                break;
            default:
                replyText = "Sorry, I only understand 'Hi' or 'Bye' at the moment.";
                break;
        }

        return Map.of(
                "status", "success",
                "reply", replyText
        );
    }
}