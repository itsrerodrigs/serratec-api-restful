package org.serratec.serratecpub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	private Double precoVenda;
	private int percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	private Double valorDesconto;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
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

	public Pedido getPedido() {
		return pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public void setPercentualDesconto(int percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
