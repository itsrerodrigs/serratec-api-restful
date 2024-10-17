package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.Produto;

public record ItemPedidoDto(
		Long id,
		int quantidade,
		Double precoVenda,
		int percentualDesconto,
		Double valorBruto,
		Double valorLiquido,
		Pedido pedido,
		Produto produto
		) {
		
		public ItemPedido toEntity() {
			ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(this.id);
            itemPedido.setQuantidade(this.quantidade);
            itemPedido.setPrecoVenda(this.precoVenda);
            itemPedido.setPercentualDesconto(this.percentualDesconto);
            itemPedido.setValorBruto(this.valorBruto);
            itemPedido.setValorLiquido(this.valorLiquido);
            itemPedido.setPedido(this.pedido);
            itemPedido.setProduto(this.produto);
            return itemPedido;
        }
		
		public static ItemPedidoDto toDto(ItemPedido itemPedido) {
			return new ItemPedidoDto(itemPedido.getId(), itemPedido.getQuantidade(), itemPedido.getPrecoVenda(),
					itemPedido.getPercentualDesconto(), itemPedido.getValorBruto(), itemPedido.getValorLiquido(),
					itemPedido.getPedido(), itemPedido.getProduto());
		}
}
