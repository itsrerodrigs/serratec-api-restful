package org.serratec.serratecpub.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private StatusPedido statusPedido;
	private Double valorTotal;
	
	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedido;
	
	private Long itemPedidoId;
	
	public Long getItemPedidoId() {
		return itemPedidoId;
	}
	
	public void setItemPedidoId(ItemPedido itemPedidoId) {
		this.itemPedidoId = itemPedidoId.getId();
	}
	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}
	public void setItensPedido(List<ItemPedido> ItemPedido) {
		ItemPedido.forEach(ip -> ip.setPedido(this));
		this.itemPedido = ItemPedido;
		
        if (!itemPedido.isEmpty()) {
            this.itemPedidoId = itemPedido.get(0).getId();  // Define o ID do primeiro ItemPedido
        }
	}
	

	public Long getId() {
		return id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
