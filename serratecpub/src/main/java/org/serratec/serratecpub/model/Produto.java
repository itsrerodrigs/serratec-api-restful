package org.serratec.serratecpub.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private int qntEstoque;
	private LocalDate dataCadastro;
	private Double valorUnitario;
	private String imagem;
	
	private Categoria categoria;

	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQntEstoque() {
		return qntEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public String getImagem() {
		return imagem;
	}

	public Categoria getCategoria() {
		return categoria;
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

	public void setQntEstoque(int qntEstoque) {
		this.qntEstoque = qntEstoque;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
