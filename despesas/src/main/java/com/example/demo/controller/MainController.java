package com.example.demo.controller;

import com.example.demo.model.Despesa;
import com.example.demo.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("gestao-despesas")
public class MainController {

    private final DespesaService service;

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
    public String exibirMinhasDespesas(Model model) {
        List<Despesa> despesas = service.listarTodasAsDespesas();
        Double total = service.listarTotal();
        model.addAttribute("total", total);
        model.addAttribute("despesas", despesas);
        return "/minhasdespesas";
    }

    @GetMapping("/sobre")
    public String exibirSobre() {
        return "/sobre";
    }

    @GetMapping("/atualizar-despesa")
    public String exibirAtualizarDespesa(Model model, Despesa despesa) {
        model.addAttribute("despesa", despesa);
        return "/atualizardespesa";
    }


}
