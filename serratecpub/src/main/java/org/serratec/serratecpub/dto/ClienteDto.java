package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Cliente;

public record ClienteDto(Long id, String nome, String cpf, LocalDate dataNascimento, String email,  String telefone,
		EnderecoDto endereco) {

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setEndereco(this.endereco.toEntity());
		return cliente;
	}

	public static ClienteDto toDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getCpf() , cliente.getDataNascimento(), cliente.getEmail(), cliente.getTelefone(),
				 EnderecoDto.toDto(cliente.getEndereco()));
	}
}