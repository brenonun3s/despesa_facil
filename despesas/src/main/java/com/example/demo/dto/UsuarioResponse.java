package com.example.demo.dto;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email) {
}
