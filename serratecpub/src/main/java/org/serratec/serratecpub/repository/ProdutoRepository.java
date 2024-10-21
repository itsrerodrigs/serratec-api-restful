package org.serratec.serratecpub.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Optional<Produto> findByNomeIgnoreCase(String nome); 
	List<Produto> findProdutoById(Long id);
}
