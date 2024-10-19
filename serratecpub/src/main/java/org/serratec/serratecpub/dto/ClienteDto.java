package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Cliente;

public record ClienteDto(Long id, String email, String nome, String telefone, LocalDate dataNascimento,
		EnderecoDto endereco) {

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setEmail(this.email);
		cliente.setNome(this.nome);
		cliente.setTelefone(this.telefone);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEndereco(this.endereco.toEntity());
		return cliente;
	}

	public static ClienteDto toDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNome(), cliente.getTelefone(),
				cliente.getDataNascimento(), EnderecoDto.toDto(cliente.getEndereco()));
	}
}