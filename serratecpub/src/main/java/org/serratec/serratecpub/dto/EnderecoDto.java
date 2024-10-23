package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.Endereco;

public record EnderecoDto(Long id,
		String cep,
		String uf,
		String cidade,
		String bairro,
		String rua,
		String numero,
		String complemento
		) {

	public Endereco toEntity() {
		Endereco endereco = new Endereco();
		endereco.setId(this.id);
		endereco.setCep(this.cep);
		endereco.setUf(this.uf);
		endereco.setCidade(this.cidade);
		endereco.setBairro(this.bairro);
		endereco.setRua(this.rua);
		endereco.setNumero(this.numero);
		endereco.setComplemento(this.complemento);
		return endereco;
	}

	public static EnderecoDto toDto(Endereco endereco) {
		return new EnderecoDto(endereco.getId(),
				endereco.getCep(),
				endereco.getUf(),
				endereco.getCidade(),
				endereco.getBairro(),
				endereco.getRua(),
				endereco.getNumero(),
				endereco.getComplemento());
	}
}