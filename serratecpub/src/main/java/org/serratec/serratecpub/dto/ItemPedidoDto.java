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
  Double valorDesconto,
  PedidoDto pedido,
  ProdutoDto produto
  ) {

  public ItemPedido toEntity() {
   ItemPedido itemPedido = new ItemPedido();
   itemPedido.setId(this.id);
   itemPedido.setQuantidade(this.quantidade);
   itemPedido.setPrecoVenda(this.precoVenda);
   itemPedido.setPercentualDesconto(this.percentualDesconto);
   itemPedido.setValorBruto(calcularValorBruto());
   itemPedido.setValorLiquido(calcularValorLiquido());
   itemPedido.setPedido(this.pedido.toEntity());
   itemPedido.setProduto(this.produto.toEntity());
   itemPedido.setValorDesconto(calcularValorDesconto());
   return itemPedido;
  }

  public static ItemPedidoDto toDto(ItemPedido itemPedido) {
   return new ItemPedidoDto(itemPedido.getId(), itemPedido.getQuantidade(), itemPedido.getPrecoVenda(),
     itemPedido.getPercentualDesconto(), itemPedido.getValorBruto(), itemPedido.getValorLiquido(),
     itemPedido.getValorDesconto(), PedidoDto.toDto(itemPedido.getPedido()),
     ProdutoDto.toDto(itemPedido.getProduto()));
  }

  public double calcularValorBruto() {
     return this.precoVenda * this.quantidade;
  }

  public double calcularValorDesconto() {
     return calcularValorBruto() * (this.percentualDesconto / 100.0);
  }

  public double calcularValorLiquido() {
     return calcularValorBruto() - calcularValorDesconto();
  }
}