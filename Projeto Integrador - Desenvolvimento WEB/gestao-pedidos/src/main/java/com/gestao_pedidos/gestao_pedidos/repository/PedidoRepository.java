/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao_pedidos.gestao_pedidos.repository;

import com.gestao_pedidos.gestao_pedidos.model.Pedido;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository {
    private Map<Long, Pedido> pedidos = new HashMap<>();
    private AtomicLong contador = new AtomicLong();

    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos.values());
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getId() == null) {
            pedido.setId(contador.incrementAndGet());
        }
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    public Pedido buscarPorId(Long id) {
        return pedidos.get(id);
    }

    public void excluir(Long id) {
        pedidos.remove(id);
    }
}
