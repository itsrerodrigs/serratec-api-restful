package org.serratec.serratecpub.model;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 8, max = 8, message = "CEP deve conter apenas 8 digitos")
	private String cep;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 3, max = 50, message = TratamentoDeErro.SizeMessage)
	private String rua;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 3, max = 50, message = TratamentoDeErro.SizeMessage)
	private String bairro;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 1, max = 50, message = TratamentoDeErro.SizeMessage)
	private String cidade;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 1, max = 10, message = TratamentoDeErro.SizeMessage)
	private String numero;	
	@Size(max = 50, message = TratamentoDeErro.SizeMessage)
	private String complemento;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 2, max = 2,message = TratamentoDeErro.SizeMessage)
	private String uf;
	
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Long getId() {
		return id;
	}
	public String getCep() {
		return cep;
	}
	public String getRua() {
		return rua;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getUf() {
		return uf;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	@Override
	public String toString() {
		return "Endereco id=" + id + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", numero=" + numero + ", complemento=" + complemento + ", uf=" + uf ;
	}
}
