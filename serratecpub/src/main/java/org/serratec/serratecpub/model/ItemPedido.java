package org.serratec.serratecpub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.Positive;

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive
	private int quantidade;
	private Double precoVenda;
	@Positive
	private int percentualDesconto;
	private double valorBruto;
	private Double valorLiquido;
	private Double valorDesconto;
	
	@JsonBackReference
	@ManyToOne
	private Pedido pedido;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
	public void setProduto(Produto produto) {
		this.produto = produto;
		setPrecoVenda(produto.getValorUnitario()*2);
	}
	
	
	public Long getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public int getPercentualDesconto() {
		return percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantidade(int quantidade) {
	    this.quantidade = quantidade;
	}

	public void setPercentualDesconto(int percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public void calcularValores() {
        this.valorBruto = this.precoVenda * this.quantidade;
        this.valorDesconto = this.valorBruto * (this.percentualDesconto / 100.0);
        this.valorLiquido = this.valorBruto - this.valorDesconto;
   }

	@Override
	public String toString() {
		return "\nQuantidade: " + quantidade + "\nValor de venda: R$" + precoVenda
				+ "\nDesconto: " + percentualDesconto + "%\nValor Bruto: R$" + valorBruto + "\nValor Liquido: R$"
				+ valorLiquido + "\nValor de Desconto: R$" + valorDesconto + "\nProdutos: " + produto.toString();
	}
	
}
