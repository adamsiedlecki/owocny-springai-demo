package net.asiedlecki.owocny.owocny_springai_demo.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }

    @GetMapping("/")
    public String joke(@RequestParam(value = "message", defaultValue = "Jakie znasz rodzaje gleb występujące w Polsce? Wymień nazwy.") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

}
