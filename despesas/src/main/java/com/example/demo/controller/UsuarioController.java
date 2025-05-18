package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.cadastrarUsuario(usuarioDTO);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao realizar cadastro: " + e.getMessage());
        }
        return "redirect:/login";
    }



    @PutMapping("/atualizar")
    public UsuarioResponse atualizar(@ModelAttribute UsuarioDTO usuarioDTO) {
        return usuarioService.atualizarUsuario(usuarioDTO);
    }
}
