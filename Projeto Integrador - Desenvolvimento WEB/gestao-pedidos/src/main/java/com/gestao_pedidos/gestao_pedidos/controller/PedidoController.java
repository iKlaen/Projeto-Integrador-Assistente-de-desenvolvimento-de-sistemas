/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao_pedidos.gestao_pedidos.controller;

/**
 *
 * @author User
 */
import com.gestao_pedidos.gestao_pedidos.model.Pedido;
import com.gestao_pedidos.gestao_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Tela de novo pedido
    @GetMapping("/novo")
    public String novoPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        return "form";
    }

    // Salvar pedido
    @PostMapping
    public String salvar(@ModelAttribute Pedido pedido) {
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    // Editar pedido
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("pedido", pedido);
        return "form";
    }

    // Excluir pedido
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }

    // Listar pedidos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "index";
    }
}
