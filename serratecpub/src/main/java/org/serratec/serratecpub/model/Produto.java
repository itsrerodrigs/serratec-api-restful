package org.serratec.serratecpub.model;

import java.text.DecimalFormat;
import java.time.LocalDate;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "\n\tCodigo do Produto: " + id 
				+ "\n\tNome: " + nome
				+ "\n\tCategoria: " + categoria 
				+ "\n\tDescricao: " + descricao
				+ "\n\tValor Unitario: R$" + df.format(valorUnitario);
	}
}
