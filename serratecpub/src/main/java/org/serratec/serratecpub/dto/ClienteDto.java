package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.Endereco;

public record ClienteDto(
		Long id,
		String email,
		String nomeCompleto,
		String telefone,
		LocalDate dataNascimento,
		Endereco endereco
		) {
	
	
		public Cliente toEntity() {
			Cliente cliente = new Cliente();
            cliente.setId(this.id);
            cliente.setEmail(this.email);
            cliente.setNomeCompleto(this.nomeCompleto);
            cliente.setTelefone(this.telefone);
            cliente.setDataNascimento(this.dataNascimento);
            cliente.setEndereco(this.endereco);
            return cliente;
        }
		
		public static ClienteDto toDto(Cliente cliente) {
			return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNomeCompleto(), cliente.getTelefone(), cliente.getDataNascimento(), cliente.getEndereco());
		}
}
