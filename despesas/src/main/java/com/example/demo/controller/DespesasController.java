package com.example.demo.controller;

import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Usuario;
import com.example.demo.service.DespesaService;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesasController {

    private final DespesaService service;
    private final UsuarioService usuarioService;

    @PostMapping("/salvar-despesa")
    public String salvar(@ModelAttribute Despesa despesa, RedirectAttributes redirectAttributes, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);

        if (usuario.isPresent()) {
            despesa.setUsuario(usuario.get());
            service.registrarDespesa(despesa);
            redirectAttributes.addFlashAttribute("mensagem", "Despesa salva com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar: usuário não encontrado");
        }

        return "redirect:/gestao-despesas/minhas-despesas";
    }


    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        service.deletar(id);
        return "redirect:/gestao-despesas/minhas-despesas";
    }

    @GetMapping("/listar-despesas")
    public String listar(Model model, Authentication authentication) {
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
        }

        return "minhasdespesas";
    }



    @PutMapping("/alterar-despesa/{id}")
    public String atualizar(@ModelAttribute Despesa despesa, RedirectAttributes redirectAttributes) {
        service.atualizar(despesa);
        redirectAttributes.addFlashAttribute("mensagem", "Despesa atualizada com sucesso");
        return "redirect:/gestao-despesas/minhas-despesas";
    }
}