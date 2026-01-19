CREATE TABLE setup_itens (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    componente VARCHAR(100) NOT NULL,
    valor DECIMAL(10,2),
    quantidade INTEGER,
    data_valor DATE
);
