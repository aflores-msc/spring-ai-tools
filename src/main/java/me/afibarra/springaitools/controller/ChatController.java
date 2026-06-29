package me.afibarra.springaitools.controller;

import me.afibarra.springaitools.tools.NewsTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;
    private final NewsTools newsTools;

    public ChatController(ChatClient chatClient, NewsTools newsTools) {
        this.chatClient = chatClient;
        this.newsTools = newsTools;
    }

    @GetMapping("/chat")
    public String chat(
            @RequestParam String prompt,
            @RequestParam(defaultValue = "default") String conversationId) {

        return chatClient.prompt()
                .user(prompt)
                .tools(newsTools)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
