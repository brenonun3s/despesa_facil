package com.example.demo.test;

import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteConexaoController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/testar-conexao")
    public String testarConexao() {
        try (Connection conn = dataSource.getConnection()) {
            return "Conexão bem-sucedida com Supabase!";
        } catch (SQLException e) {
            return e.getMessage() + "Erro ao conectar: ";
        }
    }
}
