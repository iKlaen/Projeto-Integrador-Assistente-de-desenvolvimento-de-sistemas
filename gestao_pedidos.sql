-- Criando o banco de dados
CREATE DATABASE IF NOT EXISTS gestao_pedidos;

-- Selecionando o banco de dados
USE gestao_pedidos;

-- tabela de pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(255) NOT NULL,
    representante VARCHAR(255) NOT NULL,
    valor DOUBLE NOT NULL,
    item VARCHAR(255) NOT NULL,
    situacao VARCHAR(50) NOT NULL DEFAULT 'Nao Faturado',
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


SELECT * FROM pedidos;
