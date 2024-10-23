CREATE TABLE item_pedido(
	id SERIAL PRIMARY KEY,
	quantidade INT,
	preco_venda DECIMAL,
	percentual_desconto INT,
	valor_bruto DECIMAL,
	valor_liquido DECIMAL,
	produto_id BIGINT,
	pedido_id bigint,
	
	CONSTRAINT fk_pedido FOREIGN KEY(pedido_id) 
	REFERENCES pedido(id) ON DELETE CASCADE,
	CONSTRAINT fk_produto FOREIGN KEY(produto_id)
	REFERENCES produto(id) ON DELETE CASCADE
)