package org.serratec.serratecpub.repository;

import org.serratec.serratecpub.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
