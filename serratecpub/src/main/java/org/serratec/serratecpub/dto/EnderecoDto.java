package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.Endereco;

public record EnderecoDto(Long id, String cep, String rua, String bairro, String cidade, String numero,
		String complemento, String uf) {

	public Endereco toEntity() {
		Endereco endereco = new Endereco();
		endereco.setId(this.id);
		endereco.setCep(this.cep);
		endereco.setRua(this.rua);
		endereco.setBairro(this.bairro);
		endereco.setCidade(this.cidade);
		endereco.setNumero(this.numero);
		endereco.setComplemento(this.complemento);
		endereco.setUf(this.uf);
		return endereco;
	}

	public static EnderecoDto toDto(Endereco endereco) {
		return new EnderecoDto(endereco.getId(), endereco.getCep(), endereco.getRua(), endereco.getBairro(),
				endereco.getCidade(), endereco.getNumero(), endereco.getComplemento(), endereco.getUf());
	}
}