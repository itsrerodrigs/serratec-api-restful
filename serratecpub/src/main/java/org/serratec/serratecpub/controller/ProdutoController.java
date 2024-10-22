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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@Operation(summary = "Retornar o Pedido pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produtos localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o id informado. Verifique!")
			})
	public List<ProdutoDto> obterTodosProdutos() {
		return produtoService.obterTodosProdutos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar o Pedido pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o id informado. Verifique!")
			})
	public ResponseEntity<ProdutoDto> obterProdutoPorId(@PathVariable Long id) {
		Optional<ProdutoDto> produtoDto = produtoService.obterProdutosPorId(id);
		if (!produtoDto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoDto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Retornar o Pedido pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto cadastrado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o id informado. Verifique!")
			})
	public ProdutoDto cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
		return produtoService.salvarProduto(produtoDto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Retornar o Pedido pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto deletado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o id informado. Verifique!")
			})
	public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
		if (!produtoService.apagarProduto(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.ok("Produto " + id + " excluído com sucesso!");
	}

	@PutMapping("/{id}")
	@Operation(summary = "Retornar o Pedido pelo Id", description = "Dado um determinado número de id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto alterado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o id informado. Verifique!")
			})
	public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
		Optional<ProdutoDto> produtoAlterado = produtoService.alterarProduto(id, produtoDto);
		if (!produtoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoAlterado.get());
	}
}
