package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.StatusPedido;

public record PedidoDto(
		Long id,
		LocalDate dataPedido,
		LocalDate dataEntrega,
		LocalDate dataEnvio,
		StatusPedido statusPedido,
		Double valorTotal,
		Cliente cliente
		) {
		
			public Pedido toEntity() {
				Pedido pedido = new Pedido();
				pedido.setId(this.id);
				pedido.setDataPedido(this.dataPedido);
				pedido.setDataEntrega(this.dataEntrega);
				pedido.setDataEnvio(this.dataEnvio);
				pedido.setStatusPedido(this.statusPedido);
				pedido.setValorTotal(this.valorTotal);
				pedido.setCliente(this.cliente);
				return pedido;
			}
			
			public static PedidoDto toDto(Pedido pedido) {
				return new PedidoDto(pedido.getId(), pedido.getDataPedido(), pedido.getDataEntrega(),
						pedido.getDataEnvio(), pedido.getStatusPedido(), pedido.getValorTotal(), pedido.getCliente());
			}
		
}
