CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	email VARCHAR(100) UNIQUE,
	nome_completo VARCHAR(150),
	cpf VARCHAR(14) UNIQUE,
	telefone VARCHAR(15) UNIQUE,
	data_nascimento DATE,
	id_endereco BIGINT,
	
	CONSTRAINT id_endereco FOREIGN KEY (id_endereco)
	REFERENCES endereco(id)
)