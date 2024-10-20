package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public record ItemPedidoDto(
		Long id,
		Double precoVenda,
		int quantidade,
		int percentualDesconto,
		double valorBruto,
		double valorLiquido,
		double valorDesconto,
		ProdutoDto produto) {

	public ItemPedido toEntity() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(this.id);
        itemPedido.setPrecoVenda(this.produto().toEntity().getValorUnitario());
        itemPedido.setQuantidade(this.quantidade);
        itemPedido.setPercentualDesconto(this.percentualDesconto);
        itemPedido.setProduto(this.produto.toEntity());        	
        itemPedido.calcularValores();
        return itemPedido;
    }

    public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(
            itemPedido.getId(),
            itemPedido.getPrecoVenda(),
            itemPedido.getQuantidade(),
            itemPedido.getPercentualDesconto(),
            itemPedido.getValorBruto(),
            itemPedido.getValorLiquido(),
            itemPedido.getValorDesconto(),
            ProdutoDto.toDto(itemPedido.getProduto())
        );
    }
	
   
//    public PedidoDto salvaValorTd(Pedido pedido) {
//    	double vltd = 0.0;
//    	if(pedido.getItemPedido()!= null) {
//    		for(ItemPedido ip : pedido.getItemPedido()) {
//    			ip.setPedido(pedido);
//    			vltd += ip.getValorBruto();    		}
//    	}
//    	pedido.setValorTotal(vltd);
//    	Pedido pedidoSalvo=pedidoRepository.save(pedido);
//    	return PedidoDto.toDto(pedidoSalvo);
//    }
    public double calcularValorBruto() {
    	if (this.produto != null) {
            return this.produto.toEntity().getValorUnitario() * this.quantidade;
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    public double calcularValorDesconto() {
        return calcularValorBruto() * (this.percentualDesconto / 100.0);
    }

    public double calcularValorLiquido() {
        return calcularValorBruto() - calcularValorDesconto();
    }
    
}