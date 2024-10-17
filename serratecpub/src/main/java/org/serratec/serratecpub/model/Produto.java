package org.serratec.serratecpub.model;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 3, max = 50, message = TratamentoDeErro.SizeMessage)
	private String nome;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(max = 100, message = TratamentoDeErro.SizeMessage)
	private String descricao;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	private int qntEstoque;
	private LocalDate dataCadastro = LocalDate.now();//data de cadastro vai automaticamente
	@Positive
	private Double valorUnitario;
	@Size(min = 1, max = 500, message = TratamentoDeErro.SizeMessage)
	private String imagem;
	
	
	@OneToOne(cascade= CascadeType.ALL)
	private Categoria categoria;
	
	@ManyToMany
	private List<ItemPedido> itensPedido;
	
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
