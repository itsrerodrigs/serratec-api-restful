package org.serratec.serratecpub.controller;

import java.util.Optional;

import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.service.ItemPedidoService;
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
@RequestMapping("/itenspedidos")
public class ItemPedidoController {
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	@Operation(summary = "Retornar lista de itens pedidos", description = "Dado um determinado id, será retornado o item pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Itens pedidos localizado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Não encontramos sua lista de pedidos. Tente novamente!")
			})
	public ResponseEntity<?> obterTodosItensPedidos() {
		if(itemPedidoService.obterTodosItensPedidos().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de itens pedido vazia!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(itemPedidoService.obterTodosItensPedidos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar item pedido pelo id", description = "Dado um determinado id, será retronado o item pedido pelo cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "200", description = "Item pedido informado!"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
   			@ApiResponse(responseCode = "404", description = "Não encontrado!")
   			})
	public ResponseEntity<?> obterItensPedidosPorId(@PathVariable Long id) {
		Optional<ItemPedidoDto> itemPedidoDto = itemPedidoService.obterItensPedidosPorId(id);
		if (!itemPedidoDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não existe item pedido com o id informado!");
		}
		return ResponseEntity.ok(itemPedidoDto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Retornar item pedido pelo id", description = "Dado um determinado id, será retronado o item pedido do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "200", description = "Item pedido cadastrado!"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
   			@ApiResponse(responseCode = "404", description = "Não encontrado!")
   			})
	public ItemPedidoDto cadastrarItemPedido(@RequestBody @Valid ItemPedidoDto dto) {
		return itemPedidoService.salvarItemPedido(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar item pedido pelo id", description = "Dado um determinado id, será deletado o item pedido do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "200", description = "Item pedido deletado!"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
   			@ApiResponse(responseCode = "404", description = "Não encontrado!")
   			})
	public ResponseEntity<String> deletarItemPedido(@PathVariable Long id) {
		if (!itemPedidoService.apagarItemPedido(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item pedido não encontrado!");
		}
		return ResponseEntity.ok("Item pedido " + id  + " excluído com sucesso!");
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar item pedido pelo id", description = "Dado um determinado id, será alterado o item pedido do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "200", description = "Item pedido alterado!"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
   			@ApiResponse(responseCode = "404", description = "Não encontrado!")
   			})
	public ResponseEntity<?> alterarItemPedido(@PathVariable Long id, @RequestBody @Valid ItemPedidoDto itemPedidoDto) {
		Optional<ItemPedidoDto> itemPedidoAlterado = itemPedidoService.alterarItemPedido(id, itemPedidoDto);
		if (!itemPedidoAlterado.isPresent()) {
			return ResponseEntity.status(403).body("O item desse pedido não foi alterado");
		}
		return ResponseEntity.ok(itemPedidoAlterado.get());
	}
}