package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("usuario") UsuarioDTO usuarioDTO,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        try {
            usuarioService.cadastrarUsuario(usuarioDTO);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso!");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", e.getMessage());
            model.addAttribute("usuario", usuarioDTO);
            return "cadastro";
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro inesperado: " + e.getMessage());
            model.addAttribute("usuario", usuarioDTO);
            return "cadastro";
        }
    }


    @PutMapping("/atualizar")
    public UsuarioResponse atualizar(@ModelAttribute UsuarioDTO usuarioDTO) {
        return usuarioService.atualizarUsuario(usuarioDTO);
    }
}
