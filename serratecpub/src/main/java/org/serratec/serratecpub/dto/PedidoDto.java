package org.serratec.serratecpub.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.StatusPedido;

public record PedidoDto(
		Long id, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio,
		StatusPedido statusPedido, Double valorTotal, Cliente cliente, List<ItemPedidoDto> itemPedido) {

	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setStatusPedido(this.statusPedido);
		pedido.setValorTotal(this.valorTotal);
		pedido.setCliente(this.cliente);
		
		pedido.setItensPedido(this.itemPedido.stream().map(ItemPedidoDto::toEntity).toList());

		return pedido;
	}

	public static PedidoDto toDto(Pedido pedido) {
		return new PedidoDto(
				pedido.getId(),
				pedido.getDataPedido(),
				pedido.getDataEntrega(),
				pedido.getDataEnvio(),
				pedido.getStatusPedido(),
				pedido.getValorTotal(),
				pedido.getCliente(),
				pedido.getItemPedido().stream().map(ip -> ItemPedidoDto.toDto(ip)).toList()
				);
	}

}
