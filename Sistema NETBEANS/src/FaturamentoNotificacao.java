/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class FaturamentoNotificacao implements Observer {
    @Override
    public void atualizar(String status) {
        System.out.println("Departamento de Faturamento foi notificado: O status do pedido mudou para " + status);
    }
}
