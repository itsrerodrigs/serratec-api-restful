package org.serratec.serratecpub.repository;

import java.util.Optional;

import org.serratec.serratecpub.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	Optional<Endereco> findByRuaAndNumeroAndCidadeAndUf(String rua, String numero, String cidade, String uf);
}
