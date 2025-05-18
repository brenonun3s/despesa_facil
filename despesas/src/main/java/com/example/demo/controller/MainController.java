package com.example.demo.controller;

import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Usuario;
import com.example.demo.service.DespesaService;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("gestao-despesas")
public class MainController {

    private final DespesaService service;
    private final UsuarioService usuarioService;

    @GetMapping("/cadastrar-despesa")
    public String exibirFormularioDespesa(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "/cadastrardespesa";
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/minhas-despesas")
    public String exibirMinhasDespesas(Model model, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioLogado = usuarioService.buscarPorEmail(email);

        if (usuarioLogado.isPresent()) {
            UUID usuarioId = usuarioLogado.get().getId();
            List<Despesa> despesas = service.listarDespesasPorUsuario(usuarioId);
            Double total = service.calcularTotalPorUsuario(usuarioId);

            model.addAttribute("despesas", despesas);
            model.addAttribute("total", total);
        } else {
            model.addAttribute("mensagem", "Usuário não encontrado.");
            model.addAttribute("despesas", List.of());
            model.addAttribute("total", 0.0);
        }

        return "minhasdespesas";
    }


    @GetMapping("/sobre")
    public String exibirSobre() {
        return "/sobre";
    }

    @GetMapping("/index")
    public String exibirIndex() {
        return "/index";
    }

    @GetMapping("/atualizar-despesa")
    public String exibirAtualizarDespesa(Model model, Despesa despesa) {
        model.addAttribute("despesa", despesa);
        return "/atualizardespesa";
    }

    @GetMapping("/bot")
    public String exibirBot() {
        return "/chat";
    }


}
