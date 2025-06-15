package com.example.demo.controller;

import com.example.demo.dto.ChatRequest;
import com.example.demo.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gestao-despesas/api")
public class ChatController {

    @Autowired
    private ChatGptService chatGptService;

    @PostMapping(path = "/chat", consumes = "application/json")
    public ResponseEntity<String> conversar(@RequestBody ChatRequest chatRequest) {
        if (chatRequest.getMessages() == null || chatRequest.getMessages().isEmpty()) {
            return ResponseEntity.badRequest().body("Lista de mensagens está vazia ou nula.");
        }

        String mensagem = chatRequest.getMessages().get(0).getContent();
        String resposta = chatGptService.ask(mensagem);
        return ResponseEntity.ok(resposta);
    }


}
