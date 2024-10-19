package org.serratec.serratecpub.repository;

import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
//	public default void setProdutoById(ItemPedido itemPedido, Long produtoId) {
//        Produto produto = ProdutoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto not found"));
//        itemPedido.setProduto(produto);
//    }
}
