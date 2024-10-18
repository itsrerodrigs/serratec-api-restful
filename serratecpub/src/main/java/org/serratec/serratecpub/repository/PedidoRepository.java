package org.serratec.serratecpub.repository;

import java.util.List;

import org.serratec.serratecpub.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("SELECT p FROM Pedido p JOIN p.cliente c WHERE (c.nome) ilike LOWER(CONCAT('%',:nome,'%'))")
	List<Pedido> BuscarPedidoPorNomeCliente(String nome);
	
}
