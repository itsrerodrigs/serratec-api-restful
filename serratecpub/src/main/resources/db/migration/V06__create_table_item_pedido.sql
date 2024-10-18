CREATE TABLE item_pedido(
	id SERIAL PRIMARY KEY,
	quantidade INT,
	preco_venda DECIMAL,
	percentual_desconto INT,
	valor_bruto DECIMAL,
	valor_liquido DECIMAL,
	pedido_id BIGINT,
	produto_id BIGINT,
	
	CONSTRAINT fk_pedido FOREIGN KEY(pedido_id)
	REFERENCES pedido(id),
	CONSTRAINT fk_produto FOREIGN KEY(produto_id)
	REFERENCES produto(id)
)