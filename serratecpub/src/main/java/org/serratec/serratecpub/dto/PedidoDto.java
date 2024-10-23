package org.serratec.serratecpub.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.StatusPedido;

public record PedidoDto(
		Long id, 
		StatusPedido statusPedido,
		LocalDate dataPedido, 
		LocalDate dataEnvio,
		LocalDate dataEntrega, 
		ClienteDto cliente, 
		List<ItemPedidoDto> itemPedido,
		Double valorTotal
		) {

	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setStatusPedido(this.statusPedido);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setCliente(this.cliente.toEntity());
		pedido.setItensPedido(this.itemPedido.stream().map(ItemPedidoDto::toEntity).toList());
		pedido.setValorTotal(valorTd(pedido));

		return pedido;
	}

	public static PedidoDto toDto(Pedido pedido) {
		return new PedidoDto(
				pedido.getId(),
				pedido.getStatusPedido(),
				pedido.getDataPedido(),
				pedido.getDataEnvio(),
				pedido.getDataEntrega(),
				ClienteDto.toDto(pedido.getCliente()),
				pedido.getItemPedido().stream().map(ip -> ItemPedidoDto.toDto(ip)).toList(),
				pedido.getValorTotal()
				);
	}
	
	public double valorTd(Pedido pedido) {
		double vltd = 0.0;
		if(pedido.getItemPedido()!=null) {
			for (ItemPedido item : pedido.getItemPedido()) {
                double tdValor = item.getValorLiquido();
                vltd += tdValor;
			}
			pedido.setValorTotal(vltd);
			return vltd;
			
		}
		return 0;
	}
}
