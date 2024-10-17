package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ProdutoDto;
import org.serratec.serratecpub.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProdutoController {

	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public List<ProdutoDto> obterTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> obterPorId(@PathVariable Long id) {
		Optional<ProdutoDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto cadastrarProduto(@RequestBody ProdutoDto dto) {
		return servico.salvarProduto(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaProduto(@PathVariable Long id) {
		if (!servico.apagarProduto(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDto dto) {
		Optional<ProdutoDto> produtoAlterado = servico.alterarProduto(id, dto);
		if (!produtoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoAlterado.get());
	}
}
