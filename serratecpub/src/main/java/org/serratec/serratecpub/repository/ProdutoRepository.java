package org.serratec.serratecpub.repository;

import org.serratec.serratecpub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
