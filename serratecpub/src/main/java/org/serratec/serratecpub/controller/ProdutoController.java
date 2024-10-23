package org.serratec.serratecpub.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@Operation(summary = "Retornar o produto pelo Id", description = "Dado um determinado número de id, será retornado o produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ResponseEntity<?> obterTodosProdutos() {
		if(produtoService.obterTodosProdutos().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de prodoutos vazia!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.obterTodosProdutos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar o produto pelo Id", description = "Dado um determinado número de id, será retornado o produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ResponseEntity<?> obterProdutoPorId(@PathVariable Long id) {
		Optional<ProdutoDto> produtoDto = produtoService.obterProdutosPorId(id);
		if (!produtoDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto com id informado não foi encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoDto.get());
	}
	
	@GetMapping("cadastros/{nome}")
	@Operation(summary = "Retornar o produto pelo nome", description = "Dado um determinado nome, será retornado o produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ResponseEntity<?> obterProdutoPorNome(@PathVariable String nome) {
		Optional<ProdutoDto> produtoDto = produtoService.obterProdutosPorNome(nome);
		if (!produtoDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto com nome informado não foi encontrado");
		}
		return ResponseEntity.ok(produtoDto.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Retornar o produto pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto cadastrado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ProdutoDto cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto) {
		return produtoService.salvarProduto(produtoDto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Retornar o produto pelo Id", description = "Dado um determinado número de id, será retornado o produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto deletado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
		if (!produtoService.apagarProduto(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.ok("Produto " + id + " excluído com sucesso!");
	}

	@PutMapping("/{id}")
	@Operation(summary = "Retornar o produto pelo Id", description = "Dado um determinado número de id, será retornado o produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto alterado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado um cadastro de produto no id informado. Verifique!")
			})
	public ResponseEntity<?> alterarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto) {
		Optional<ProdutoDto> produtoAlterado = produtoService.alterarProduto(id, produtoDto);
		if (!produtoAlterado.isPresent()) {
			return ResponseEntity.status(403).body("O produto não foi alterado");
		}
		return ResponseEntity.ok(produtoAlterado.get());
	}
}
