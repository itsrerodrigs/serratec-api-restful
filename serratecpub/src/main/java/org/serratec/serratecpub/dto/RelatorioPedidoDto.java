
package org.serratec.serratecpub.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;

public record RelatorioPedidoDto(Long id, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio,
		Cliente cliente, List<ItemPedidoDto> itemPedido, Double valorTotal, Double valorTotalDesconto) {
	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setCliente(this.cliente);
		pedido.setItensPedido(this.itemPedido.stream().map(ItemPedidoDto::toEntity).collect(Collectors.toList()));
		pedido.setValorTotal(valorTd(pedido));
		pedido.setValorTotalDesconto(valorTotalDesconto(pedido));

		return pedido;
	}

	public static RelatorioPedidoDto toDto(Pedido pedido) {
		return new RelatorioPedidoDto(
				pedido.getId(),
				pedido.getDataPedido(),
				pedido.getDataEntrega(),
				pedido.getDataEnvio(),
				pedido.getCliente(),
				pedido.getItemPedido().stream().map(ItemPedidoDto::toDto).collect(Collectors.toList()),
				pedido.getValorTotal(),
				pedido.getValorTotalDesconto());
	}

	public double valorTd(Pedido pedido) {
		double vltd = 0.0;
		if (pedido.getItemPedido() != null) {
			for (ItemPedido item : pedido.getItemPedido()) {
				double tdValor = item.getValorLiquido();
				vltd += tdValor;
			}
			pedido.setValorTotal(vltd);
			return vltd;
		}
		return 0;
	}

	public double valorTotalDesconto(Pedido pedido) {
	    double totalDesconto = 0.0;
	    if (pedido.getItemPedido() != null) {
	        for (ItemPedido item : pedido.getItemPedido()) {
	            Double desconto = item.getValorDesconto();
	            if (desconto != null) {
	                totalDesconto += desconto;
	                pedido.setValorTotalDesconto(totalDesconto);
	            }
	        }
	        return totalDesconto;
	    }
	    return 0;
	}

	public String gerarRelatorio() {
		StringBuilder relatorio = new StringBuilder();
		relatorio.append("SERRATECPUB\n\n");
		relatorio.append("Relatório do Pedido:\n");
		relatorio.append("ID do pedido: ").append(this.id).append("\n");
		relatorio.append("Data do Pedido: ").append(this.dataPedido).append("\n");
		relatorio.append("Cliente: ").append(this.cliente.getNome()).append("\n");
		relatorio.append("Itens do Pedido:\n\n");
		for (ItemPedidoDto item : this.itemPedido) {
			relatorio.append("  ID do Item: ").append(item.id()).append("\n");
			relatorio.append("  Produto: ").append(item.produto().nome()).append("\n");
			relatorio.append("  Quantidade: ").append(item.quantidade()).append("\n");
			relatorio.append("  Preço de Venda: ").append(item.precoVenda()).append("\n");
			relatorio.append("  Valor Bruto: ").append(item.valorBruto()).append("\n");
			relatorio.append("  Percentual de desconto: ").append(item.percentualDesconto()).append('%').append("\n");
			relatorio.append("  Valor Líquido: ").append(item.valorLiquido()).append("\n\n");
		}
		relatorio.append("Valor Total de Desconto: ").append(this.valorTotalDesconto).append("\n");
		relatorio.append("Valor Total do pedido: ").append(this.valorTotal).append("\n");
		return relatorio.toString();
	}
}
