/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao_pedidos.gestao_pedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author wilso
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}

