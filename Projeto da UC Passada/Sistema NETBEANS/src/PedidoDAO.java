/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PedidoDAO {

    public void inserirPedido(String cliente, String representante, double valor, String item) {
        String sql = "INSERT INTO pedidos (cliente, representante, valor, item) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Preencher os parâmetros da consulta
            pstmt.setString(1, cliente);
            pstmt.setString(2, representante);
            pstmt.setDouble(3, valor);
            pstmt.setString(4, item);

            // Executar a inserção
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido: " + ex.getMessage());
        }
    }
    public void excluirPedido(String cliente, String representante, String item) {
        String sql = "DELETE FROM pedidos WHERE cliente = ? AND representante = ? AND item = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cliente);
            stmt.setString(2, representante);
            stmt.setString(3, item);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado para exclusão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir o pedido: " + e.getMessage());
        }
    }
    public void faturarPedido(String cliente, String representante, String item) {
        String sql = "UPDATE pedidos SET situacao = 'Faturado' WHERE cliente = ? AND representante = ? AND item = ? AND situacao = 'Não faturado'";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cliente);
            stmt.setString(2, representante);
            stmt.setString(3, item);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pedido faturado com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado para faturamento ou já faturado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao faturar o pedido: " + e.getMessage());
        }
    }
}
