package org.serratec.serratecpub.model;

import jakarta.persistence.Entity;

@Entity
public class Endereco {
	private Long id;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String numero;
	private String complemento;
	private String uf;
	
	
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
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}
