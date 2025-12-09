package net.asiedlecki.owocny.owocny_springai_demo.api;

import net.asiedlecki.owocny.owocny_springai_demo.model.StructuredListResponse;
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
    public StructuredListResponse joke(@RequestParam(value = "message", defaultValue = "Jakie znasz rodzaje gleb występujące w Polsce? Wymień nazwy.") String message) {
        return chatClient.prompt()
                .user(message)
                .system("""
                        Gdy użytkownik pyta o wymienienie rzeczy jakiegoś rodzaju, napisz krótki wstęp - maksymalnie 2 zdania.
                        Następnie wymień te rzeczy bez zbędnego przedłużania.
                        
                        Odpowiadaj **wyłącznie** czystym JSON — bez dodatkowych tekstów, znaczników, tokenów lub wyjaśnień.
                        """)
                .call()
                .entity(StructuredListResponse.class);
    }

}
