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
	private ProdutoRepository produtoRepository;

	public List<ProdutoDto> obterTodosProdutos() {
		return produtoRepository.findAll().stream().map(p -> ProdutoDto.toDto(p)).toList();
	}

	public Optional<ProdutoDto> obterProdutosPorId(Long id) {
		if (!produtoRepository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ProdutoDto.toDto(produtoRepository.findById(id).get()));
	}

	public Optional<ProdutoDto> obterProdutosPorNome(String nome) {
		if (produtoRepository.findByNomeIgnoreCase(nome).isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(ProdutoDto.toDto(produtoRepository.findByNomeIgnoreCase(nome).get()));
	}

	public ProdutoDto salvarProduto(ProdutoDto produtoDto) {
		Produto produtoEntity = produtoRepository.save(produtoDto.toEntity());
		return ProdutoDto.toDto(produtoEntity);
	}

	public boolean apagarProduto(Long id) {
		if (!produtoRepository.existsById(id)) {
			return false;
		}
		produtoRepository.deleteById(id);
		return true;
	}

	public Optional<ProdutoDto> alterarProduto(Long id, ProdutoDto produtoDto) {
		if (!produtoRepository.existsById(id)) {
			return Optional.empty();
		}
		Produto produtoEntity = produtoDto.toEntity();
		produtoEntity.setId(id);
		produtoRepository.save(produtoEntity);
		return Optional.of(ProdutoDto.toDto(produtoEntity));
	}
}
