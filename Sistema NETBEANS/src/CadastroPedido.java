/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroPedido extends JFrame {

    private JTextField clienteField, representanteField,valorField,itemField;
    private JButton concluirButton, sairButton;
    private GerenciamentoDePedidos gerenciamento;

    public CadastroPedido(GerenciamentoDePedidos gerenciamento) {
        this.gerenciamento = gerenciamento;

        // Configuração da janela
        setTitle("Cadastro de Pedido");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // Campos de texto
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setBounds(10, 10, 100, 25);
        panel.add(clienteLabel);

        clienteField = new JTextField();
        clienteField.setBounds(120, 10, 250, 25);
        panel.add(clienteField);

        JLabel representanteLabel = new JLabel("Representante:");
        representanteLabel.setBounds(10, 50, 100, 25);
        panel.add(representanteLabel);

        representanteField = new JTextField();
        representanteField.setBounds(120, 50, 250, 25);
        panel.add(representanteField);

        JLabel valorLabel = new JLabel("Valor (R$):");
        valorLabel.setBounds(10, 90, 100, 25);
        panel.add(valorLabel);

        valorField = new JTextField(10);
        valorField.setBounds(120, 90, 250, 25);
        panel.add(valorField);
        valorField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

        // Permitir apenas números, ponto e vírgula
        if (!Character.isDigit(c) && c != '.' && c != ',') {
            e.consume();  // Ignora o caractere
        }

        // Permite apenas um ponto ou vírgula
        if ((c == '.' || c == ',') && (valorField.getText().contains(".") || valorField.getText().contains(","))) {
            e.consume();
        }
    }
});
        

        JLabel itemLabel = new JLabel("Item:");
        itemLabel.setBounds(10, 130, 100, 25);
        panel.add(itemLabel);

        itemField = new JTextField();
        itemField.setBounds(120, 130, 250, 25);
        panel.add(itemField);

        // Botões
        concluirButton = new JButton("Concluir");
        concluirButton.setBounds(120, 180, 100, 25);
        panel.add(concluirButton);

        sairButton = new JButton("Sair");
        sairButton.setBounds(230, 180, 100, 25);
        panel.add(sairButton);

        // Ações dos botões
        concluirButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String cliente = clienteField.getText();
        String representante = representanteField.getText();
        String valorStr = valorField.getText();
        String item = itemField.getText();

        // Verificação de campos obrigatórios
        if (cliente.isEmpty() || representante.isEmpty() || valorStr.isEmpty() || item.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do valor como double
        double valor;
        try {
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Inserir o pedido no banco de dados
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.inserirPedido(cliente, representante, valor, item);

        // Atualizar a tabela de pedidos na tela de Gerenciamento
        gerenciamento.addPedido(cliente, representante, valorStr, item);

        dispose();
    }
});


        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
