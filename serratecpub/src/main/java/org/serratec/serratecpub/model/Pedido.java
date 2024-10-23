package org.serratec.serratecpub.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.serratecpub.util.TratamentoDeErro;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;
	private double valorTotal;
	private Double valorTotalDesconto = 0.0;
	
	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedido =new ArrayList<>();
	
	public List<ItemPedido> getItemPedido() {
		return itemPedido;
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

	public double getValorTotal() {
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = LocalDate.now();
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = LocalDate.now().plusDays(15);
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = LocalDate.now().plusDays(2);
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
		
		if (statusPedido == StatusPedido.ENVIADO) {
            this.dataPedido = LocalDate.now();
            this.dataEnvio = LocalDate.now().plusDays(2);
            this.dataEntrega = null;
		}
		else if(statusPedido == StatusPedido.EM_PRODUCAO) {
			this.dataPedido = LocalDate.now();
			this.dataEnvio = null;
			this.dataEntrega = null;
		}
		else if (statusPedido == StatusPedido.SAIU_DA_TRANSPORTADORA) {
            this.dataPedido = LocalDate.now();
            this.dataEnvio = LocalDate.now();
            this.dataEntrega = null;
		}
		else if (statusPedido == StatusPedido.ENTREGUE) {
            this.dataPedido = LocalDate.now();
            this.dataEnvio = LocalDate.now().plusDays(2);
            this.dataEntrega = LocalDate.now().plusDays(15);
        }
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void setItensPedido(List<ItemPedido> ItemPedido) {
		ItemPedido.forEach(ip -> ip.setPedido(this));
		this.itemPedido = ItemPedido;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotalDesconto() {
	    return valorTotalDesconto;
	}

	public void setValorTotalDesconto(Double valorTotalDesconto) {
	    this.valorTotalDesconto = valorTotalDesconto;
	}
  
	@Override
    public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "SERRATECPUB - DETALHES DO PEDIDO"
				+"\n\nNumero do Pedido: " + id
                + " | Data do Pedido: " + dataPedido 
                + "\nStatus do Pedido: " + statusPedido
                + "\n\nCliente: " + cliente.getNome() 
                + "\nCPF: " + TratamentoDeErro.formataCpf(cliente.getCpf())
                + "\n\nItens do Pedido: " + itemPedido.toString()
                + "\n\nValor Total do Pedido: R$" + df.format(valorTotal);
    }
}