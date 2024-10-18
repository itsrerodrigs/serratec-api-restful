CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50),
	descricao VARCHAR(100),
	qtd_estoque INT,
	data_cadastro DATE,
	valor_unitario DECIMAL,
	imagem VARCHAR(500),
	categoria_id BIGINT,
	
	CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
)