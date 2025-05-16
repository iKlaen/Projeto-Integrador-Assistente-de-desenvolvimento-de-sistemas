/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao_pedidos.gestao_pedidos.controller;

/**
 *
 * @author User
 */
import com.gestao_pedidos.gestao_pedidos.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/")
    public String telaLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
}

