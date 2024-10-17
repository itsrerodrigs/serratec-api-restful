CREATE TABLE pedido(
	id SERIAL PRIMARY KEY,
	data_pedido DATE,
	data_entrega DATE,
	data_envio DATE,
	status VARCHAR(15),
	valor_total DECIMAL,
	id_cliente BIGINT,
	
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
	REFERENCES cliente(id)
)