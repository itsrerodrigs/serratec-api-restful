CREATE TABLE pedido(
	id SERIAL PRIMARY KEY,
	data_pedido DATE,
	data_entrega DATE,
	data_envio DATE,
	status_pedido varchar(20),
	valor_total DECIMAL,
	cliente_id BIGINT,
	
	CONSTRAINT fk_cliente FOREIGN KEY (cliente_id)
	REFERENCES cliente(id) ON DELETE CASCADE
	
)