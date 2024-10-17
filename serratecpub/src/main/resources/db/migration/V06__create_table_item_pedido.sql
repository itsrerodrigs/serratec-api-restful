CREATE TABLE item_pedido(
	id SERIAL PRIMARY KEY,
	quantidade INT,
	preco_venda DECIMAL,
	percentual_desconto INT,
	valor_bruto DECIMAL,
	valor_liquido DECIMAL,
	id_pedido BIGINT,
	id_produto BIGINT,
	
	CONSTRAINT id_pedido FOREIGN KEY(id_pedido)
	REFERENCES pedido(id),
	CONSTRAINT id_produto FOREIGN KEY(id_produto)
	REFERENCES produto(id)
)