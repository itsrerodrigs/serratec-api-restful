package org.serratec.serratecpub.repository;

import java.util.List;

import org.serratec.serratecpub.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
