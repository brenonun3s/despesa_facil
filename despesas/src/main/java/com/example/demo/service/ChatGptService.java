    package com.example.demo.service;

    import com.example.demo.dto.ChatRequest;
    import com.example.demo.dto.ChatResponse;

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.stereotype.Service;
    import org.springframework.web.reactive.function.client.WebClient;

    import java.util.List;

    @Service
    public class ChatGptService {

        @Value("${openai.api.key}")
        private String apiKey;

        private final WebClient webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        public String ask(String prompt) {
            System.out.println("API Key: " + apiKey); // só para debug, remova depois
            ChatRequest.Message mensagem = new ChatRequest.Message("user", prompt);
            ChatRequest request = new ChatRequest("gpt-3.5-turbo", List.of(mensagem));


            return webClient.post()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(ChatResponse.class)
                    .map(ChatResponse::getFirstMessage)
                    .block();
        }
    }


