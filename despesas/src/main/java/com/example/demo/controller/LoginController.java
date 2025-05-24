package com.example.demo.controller;

import com.example.demo.model.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }
        return "cadastro";
    }


}
