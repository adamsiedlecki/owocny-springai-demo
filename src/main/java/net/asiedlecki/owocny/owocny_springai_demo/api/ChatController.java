package net.asiedlecki.owocny.owocny_springai_demo.api;

import net.asiedlecki.owocny.owocny_springai_demo.model.StructuredListResponse;
import net.asiedlecki.owocny.owocny_springai_demo.tools.TaxTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, TaxTools taxTools) {
        this.chatClient = builder
                .defaultTools(taxTools)
                .build();
    }

    @GetMapping("/")
    public StructuredListResponse question(@RequestParam(value = "message", defaultValue = "Wymień klasy gleby w Polsce i stawki podatku rolnego dla każdej z nich.") String message) {
        return chatClient.prompt()
                .user(message)
                .system("""
                        Gdy użytkownik pyta o wymienienie rzeczy jakiegoś rodzaju, napisz krótki wstęp - maksymalnie 2 zdania.
                        Następnie wymień te rzeczy wraz z informacjami które chce użytkownik, ale bez zbędnego przedłużania.
                        """)
                .call()
                .entity(StructuredListResponse.class);
    }

}
