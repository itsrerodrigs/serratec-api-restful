package org.serratec.serratecpub.repository;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("SELECT p FROM Pedido p "
			+ "JOIN p.cliente c "
			+ "WHERE TRANSLATE(c.nome, "
			+ "'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇáàãâäéèêëíìîïóòõôöúùûüç',"
			+ " 'AAAAAEEEEIIIIOOOOOUUUUCaaaaaeeeeiiiiooooouuuuc') "
			+ "ILIKE CONCAT('%', TRANSLATE(:nome, "
			+ "'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇáàãâäéèêëíìîïóòõôöúùûüç', "
			+ "'AAAAAEEEEIIIIOOOOOUUUUCaaaaaeeeeiiiiooooouuuuc'), '%')")
	List<Pedido> BuscarPedidoPorNomeCliente(String nome);
	
	List<Pedido> findByDataPedido(LocalDate dataPedido);
	
	List<Pedido> findByStatusPedido(StatusPedido status);
	
}
