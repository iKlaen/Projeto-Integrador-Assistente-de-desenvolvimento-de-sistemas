/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerenciamentoDePedidos extends JFrame {
    private int pedidoNumero = 1; 
    private JTable table;
    private DefaultTableModel model;
    private JButton novoPedidoButton, excluirPedidoButton, faturarPedidoButton;

    public GerenciamentoDePedidos() {
        // Configuração da janela
       setTitle("Gerenciamento de Pedidos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação do painel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // Tabela de pedidos
       String[] columnNames = {"Cliente", "Valor (R$)", "Representante", "Item", "Situação", "Data"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 700, 300);
        panel.add(scrollPane);

        // Botões
       novoPedidoButton = new JButton("Novo Pedido");
        novoPedidoButton.setBounds(600, 370, 150, 30);
        panel.add(novoPedidoButton);

        excluirPedidoButton = new JButton("Excluir Pedido");
        excluirPedidoButton.setBounds(600, 410, 150, 30);
        panel.add(excluirPedidoButton);

        faturarPedidoButton = new JButton("Faturar Pedido");
        faturarPedidoButton.setBounds(600, 450, 150, 30);
        panel.add(faturarPedidoButton);

        // Ações dos botões
        novoPedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroPedido cadastroPedido = new CadastroPedido(GerenciamentoDePedidos.this);
                cadastroPedido.setVisible(true);
            }
        });

    excluirPedidoButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String cliente = (String) model.getValueAt(selectedRow, 1); // Supondo que a coluna do cliente é a 1
            String representante = (String) model.getValueAt(selectedRow, 3); // Coluna do representante
            String item = (String) model.getValueAt(selectedRow, 2); // Coluna do item

            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o pedido?", "Excluir Pedido", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Excluir do banco de dados
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.excluirPedido(cliente, representante, item);

                // Remover da tabela
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum pedido selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});

 faturarPedidoButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String cliente = (String) model.getValueAt(selectedRow, 1); // Coluna do cliente
            String representante = (String) model.getValueAt(selectedRow, 3); // Coluna do representante
            String item = (String) model.getValueAt(selectedRow, 2); // Coluna do item
            String situacao = (String) model.getValueAt(selectedRow, 4); // Coluna da situação

            if ("Não faturado".equals(situacao)) {
                // Faturar o pedido no banco de dados
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.faturarPedido(cliente, representante, item);

                // Atualizar a tabela
                model.setValueAt("Faturado", selectedRow, 4);  // Altera a coluna "Situação" para "Faturado"
            } else {
                JOptionPane.showMessageDialog(null, "Este pedido já foi faturado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum pedido selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});
 }
    public static void main(String[] args) {
        GerenciamentoDePedidos frame = new GerenciamentoDePedidos();
        frame.setVisible(true);
    }
    public void addPedido(String cliente, String representante, String valor, String item) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataAtual = LocalDate.now().format(formatter);
        model.addRow(new Object[]{pedidoNumero, cliente, valor, representante, "Não faturado", dataAtual});
        pedidoNumero++;
}
    
}
