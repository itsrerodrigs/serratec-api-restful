CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50),
	categoria VARCHAR(20),
	descricao VARCHAR(100) UNIQUE,
	qtd_estoque INT,
	data_cadastro DATE,
	valor_unitario DECIMAL,
	imagem VARCHAR(500)
)