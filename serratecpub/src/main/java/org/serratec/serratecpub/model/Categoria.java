package org.serratec.serratecpub.model;

import jakarta.persistence.Entity;

@Entity
public class Categoria {
	private Long id;
	private String nome;
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
