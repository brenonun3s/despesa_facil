package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatResponse {
    private List<Choice> choices;

    public String getFirstMessage() {
        return choices != null && !choices.isEmpty() && choices.get(0).getMessage() != null
                ? choices.get(0).getMessage().getContent()
                : "Resposta vazia da IA.";
    }

    @Getter
    @Setter
    public static class Choice {
        private Message message;
    }

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;
    }
}
