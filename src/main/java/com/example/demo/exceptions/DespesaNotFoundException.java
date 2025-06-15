package com.example.demo.exceptions;

import java.util.UUID;

public class DespesaNotFoundException extends RuntimeException {
    public DespesaNotFoundException(String id) {
        super("Despesa com o ID" + UUID.fromString(id) + " não localizado!");

    }
}
