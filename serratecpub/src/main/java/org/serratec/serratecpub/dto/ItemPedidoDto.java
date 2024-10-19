package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Produto;

public record ItemPedidoDto(Long id, int quantidade, Double precoVenda, int percentualDesconto, Double valorBruto,
		Double valorLiquido, Double valorDesconto, PedidoDto pedido, ProdutoDto produto) {

	public ItemPedido toEntity() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(this.id);
        itemPedido.setQuantidade(this.quantidade);
        itemPedido.setPrecoVenda(this.produto.toEntity().getValorUnitario() * 2);
        itemPedido.setPercentualDesconto(this.percentualDesconto);
        if (this.pedido != null) {
            itemPedido.setPedido(this.pedido.toEntity());
        }
        itemPedido.setProduto(this.produto.toEntity());
        itemPedido.calcularValores();
        return itemPedido;
    }

    public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(
            itemPedido.getId(),
            itemPedido.getQuantidade(),
            itemPedido.getPrecoVenda(),
            itemPedido.getPercentualDesconto(),
            itemPedido.getValorBruto(),
            itemPedido.getValorLiquido(),
            itemPedido.getValorDesconto(),
            PedidoDto.toDto(itemPedido.getPedido()),
            ProdutoDto.toDto(itemPedido.getProduto())
        );
    }

    public Double calcularValorBruto() {
        return this.precoVenda * this.quantidade;
    }

    public Double calcularValorDesconto() {
        return calcularValorBruto() * (this.percentualDesconto / 100.0);
    }

    public Double calcularValorLiquido() {
        return calcularValorBruto() - calcularValorDesconto();
    }
}