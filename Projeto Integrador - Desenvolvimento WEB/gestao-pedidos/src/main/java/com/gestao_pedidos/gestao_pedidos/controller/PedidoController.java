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
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Listar todos os pedidos
    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        model.addAttribute("pedidos", pedidoRepository.listarTodos());
        return "index";
    }

    // Exibir formulário para novo pedido
    @GetMapping("/novo")
    public String novo(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        model.addAttribute("pedido", new Pedido());
        return "form";
    }

    // Salvar novo pedido
    @PostMapping
    public String salvar(@ModelAttribute Pedido pedido, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        pedidoRepository.salvar(pedido);
        return "redirect:/pedidos";
    }

    // Editar pedido existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        Pedido pedido = pedidoRepository.buscarPorId(id);
        if (pedido != null) {
            model.addAttribute("pedido", pedido);
            return "form";
        } else {
            return "redirect:/pedidos";
        }
    }

    // Atualizar pedido existente
    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Pedido pedido, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        pedidoRepository.salvar(pedido);
        return "redirect:/pedidos";
    }

    // Excluir pedido
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        pedidoRepository.excluir(id);
        return "redirect:/pedidos";
    }

    // Alterar situação do pedido (faturado ou cancelado)
    @GetMapping("/situacao/{id}/{novaSituacao}")
    public String alterarSituacao(@PathVariable Long id, @PathVariable String novaSituacao, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        Pedido pedido = pedidoRepository.buscarPorId(id);
        if (pedido != null) {
            pedido.setSituacao(novaSituacao);
            pedidoRepository.salvar(pedido);
        }
        return "redirect:/pedidos";
    }
}

