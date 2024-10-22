package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.util.VerificaCpf;

public record ClienteDto(Long id, String email, String cpf, String nome, String telefone, LocalDate dataNascimento,
		EnderecoDto endereco) {

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEndereco(this.endereco.toEntity());
		return cliente;
	}

	public static ClienteDto toDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getCpf() , cliente.getEmail(), cliente.getTelefone(),
				cliente.getDataNascimento(), EnderecoDto.toDto(cliente.getEndereco()));
	}
}