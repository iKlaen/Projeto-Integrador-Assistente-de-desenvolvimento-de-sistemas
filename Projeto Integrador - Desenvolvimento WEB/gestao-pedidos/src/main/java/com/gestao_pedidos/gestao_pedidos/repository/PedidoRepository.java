/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gestao_pedidos.gestao_pedidos.repository;

import com.gestao_pedidos.gestao_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilso
 */
public interface PedidoRepository extends JpaRepository <Pedido, Long> {
}
