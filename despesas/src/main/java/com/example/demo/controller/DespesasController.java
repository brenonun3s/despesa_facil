package com.example.demo.controller;

import com.example.demo.model.Despesa;
import com.example.demo.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesasController {

    private final DespesaService service;

    @PostMapping("/salvar-despesa")
    public String salvar(@ModelAttribute Despesa despesa, RedirectAttributes redirectAttributes) {
        service.registrarDespesa(despesa);
        redirectAttributes.addFlashAttribute("mensagem", "Despesa salva com sucesso");
        return "redirect:/gestao-despesas/minhas-despesas";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        service.deletar(id);
        return "redirect:/gestao-despesas/minhas-despesas";
    }

    @GetMapping("/listar-despesas")
    public String listar(Model model) {
        List<Despesa> despesas = service.listarTodasAsDespesas();
        Double total = service.listarTotal();
        model.addAttribute("despesas", despesas);
        model.addAttribute("total", total);
        return "minhasdespesas";
    }

    @PutMapping("/alterar-despesa/{id}")
    public String atualizar(@ModelAttribute Despesa despesa, RedirectAttributes redirectAttributes) {
        service.atualizar(despesa);
        redirectAttributes.addFlashAttribute("mensagem", "Despesa atualizada com sucesso");
        return "redirect:/gestao-despesas/minhas-despesas";
    }
}