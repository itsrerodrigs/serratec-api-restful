package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ProdutoDto;
import org.serratec.serratecpub.model.Produto;
import org.serratec.serratecpub.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repositorio;
	
	public List<ProdutoDto> obterTodos(){
		return repositorio.findAll().stream().map(p -> ProdutoDto.toDto(p)).toList();
	}
	
	public Optional<ProdutoDto> obterPorId(Long id){
		if(!repositorio.existsById(id)) {
			return Optional.empty();	
			}
		return Optional.of(ProdutoDto.toDto(repositorio.findById(id).get()));
	}
	public ProdutoDto salvarProduto(ProdutoDto dto) {
		Produto produtoEntity = repositorio.save(dto.toEntity());
		return ProdutoDto.toDto(produtoEntity);
	}
	public boolean apagarProduto(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	public Optional<ProdutoDto> alterarProduto(Long id, ProdutoDto dto){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Produto produtoEntity = dto.toEntity();
		produtoEntity.setId(id);
		repositorio.save(produtoEntity);
		return Optional.of(ProdutoDto.toDto(produtoEntity));
	}
}
