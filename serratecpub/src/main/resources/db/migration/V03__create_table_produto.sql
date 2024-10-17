CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50),
	descricao VARCHAR(100),
	qtd_estoque INT,
	data_cadastro DATE,
	valor_unitario DECIMAL,
	imagem VARCHAR(500),
	id_categoria BIGINT,
	
	CONSTRAINT id_categoria FOREIGN KEY(id_categoria)
	REFERENCES categoria(id)
)