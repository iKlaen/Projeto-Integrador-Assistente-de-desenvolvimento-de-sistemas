/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Observer> observadores = new ArrayList<>(); // Lista de observadores
    private String status; // Estado do pedido
    
     // Métodos para adicionar e remover observadores
    public void adicionarObservador(Observer observer) {
        observadores.add(observer);
    }

    public void removerObservador(Observer observer) {
        observadores.remove(observer);
    }

    // Método para alterar o status do pedido
    public void setStatus(String status) {
        this.status = status;
        notificarObservadores();
    }

    // Método para notificar todos os observadores sobre a mudança de status
    private void notificarObservadores() {
        for (Observer observer : observadores) {
            observer.atualizar(status);
        }}
    
    private String cliente;
    private String representante;
    private double valor;
    private String item;
    private String situacao;
    private String data;

    public Pedido(String cliente, String representante, double valor, String item, String situacao, String data) {
        this.cliente = cliente;
        this.representante = representante;
        this.valor = valor;
        this.item = item;
        this.situacao = situacao;
        this.data = data;
    }

    // Getters e setters
    public String getCliente() { return cliente; }
    public String getRepresentante() { return representante; }
    public double getValor() { return valor; }
    public String getItem() { return item; }
    public String getSituacao() { return situacao; }
    public String getData() { return data; }
}