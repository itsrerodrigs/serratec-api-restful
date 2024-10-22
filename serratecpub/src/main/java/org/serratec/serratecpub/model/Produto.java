package org.serratec.serratecpub.model;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecpub.util.TratamentoDeErro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 2, max = 50, message = TratamentoDeErro.SizeMessage)
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoriaNome categoria;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(max = 100, message = TratamentoDeErro.SizeMessage)
	private String descricao;
	

	@Positive
	private int qtdEstoque;
	
	@NotNull
	private LocalDate dataCadastro;
	
	
	@Positive
	private Double valorUnitario;
	
	@Size(min = 1, max = 500, message = TratamentoDeErro.SizeMessage)
	private String imagem;
	
	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public CategoriaNome getCategoria() {
		return categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCategoria(CategoriaNome categoria) {
		this.categoria = categoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = LocalDate.now();
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "\nCodigo: " + id + "\nNome: " + nome + "\nCategoria: " + categoria + "\nDescricao: " + descricao
				+ "\nEstoque: " + qtdEstoque + "\nData Cadastro: " + dataCadastro + "\nValor Unitario: R$" + valorUnitario
				+ "\nImagem: " + imagem;
	}
	
	
}
