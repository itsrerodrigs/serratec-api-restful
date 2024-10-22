package org.serratec.serratecpub.repository;

import java.util.List;

import org.serratec.serratecpub.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
  
	@Query("SELECT c FROM Cliente c "
			+ "WHERE TRANSLATE(c.nome, "
			+ "'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇáàãâäéèêëíìîïóòõôöúùûüç',"
			+ " 'AAAAAEEEEIIIIOOOOOUUUUCaaaaaeeeeiiiiooooouuuuc') "
			+ "ILIKE CONCAT('%', TRANSLATE(:nome, "
			+ "'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇáàãâäéèêëíìîïóòõôöúùûüç', "
			+ "'AAAAAEEEEIIIIOOOOOUUUUCaaaaaeeeeiiiiooooouuuuc'), '%')")
	List<Cliente> BuscarClientePorNome(String nome);
	
	List<Cliente> findByCpf(String cpf);
}