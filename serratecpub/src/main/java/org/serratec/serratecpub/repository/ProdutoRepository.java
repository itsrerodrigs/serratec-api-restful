package org.serratec.serratecpub.repository;

import java.util.List;

import org.serratec.serratecpub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Id;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findProdutoById(Long id);
}
