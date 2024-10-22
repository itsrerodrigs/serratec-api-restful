package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.PedidoDto;
import org.serratec.serratecpub.dto.RelatorioPedidoDto;
import org.serratec.serratecpub.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Retornar lista de pedidos", description = "Dado um determinado id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido localizado!"),
			
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Pedido nao encontrado!")})
	public List<PedidoDto> obterTodosPedidos() {
		return pedidoService.obterTodosPedidos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar o pedido pelo id", description = "Dado um determinado id, será retornado o pedido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido localizado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Id não encontrado!")})
	public ResponseEntity<?> obterPedidosPorId(@PathVariable Long id) {
		Optional<PedidoDto> pedidoDto = pedidoService.obterPedidosPorId(id);
		if (!pedidoDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro! Id do pedido não encontrado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDto);
	}

	
	@GetMapping("/cliente/{nome}")
	@Operation(summary = "Retornar cliente pelo nome", description = "Dado um determinado nome, será retornado o cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Caso a lista esteja vazia, é porque não existe cliente com esse nome. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Pedido localizado!"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Nome de cliente não encontrado!") })
	public List<PedidoDto> obterPedidosPorNomeCliente(@PathVariable String nome) {
		return pedidoService.obterPedidosPorNomeCliente(nome);
	}
	
	@GetMapping("/relatorio/{id}")
	@Operation(summary = "Obter relatório do pedido pelo id", description = "Dado um determinado id, o relatório do pedido será gerado!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido localizado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado!")})
	public ResponseEntity<String> imprimirRelatorioPedido(@PathVariable Long id) {
	  Optional<RelatorioPedidoDto> relatorioPedidoDto = pedidoService.obterRelatorioPedido(id);
	  if (!relatorioPedidoDto.isPresent()) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
	  }
	  String relatorio = relatorioPedidoDto.get().gerarRelatorio();
	  System.out.println(relatorio);
	  return ResponseEntity.ok(relatorio);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Cadastro de pedido", description = "Criação de pedido com informações de cliente itempedido e produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido localizado!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Id não encontrado!") })
	public PedidoDto cadastrarPedido(@RequestBody PedidoDto pedidoDto) {
		return pedidoService.salvarPedido(pedidoDto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar pedido pelo id", description = "Dado um determinado id, será deletado o pedido do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido deletado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado!")})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> deletarPedido(@PathVariable Long id, PedidoDto pedidoDto) {
		if (!pedidoService.apagarPedido(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
		}
		return ResponseEntity.ok("Pedido excluído com sucesso");
	}
	@PutMapping("/{id}")
	@Operation(summary = "Alterar pedido pelo id", description = "Dado um determinado id, será alterado o pedido do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido alterado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado!")})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
		Optional<PedidoDto> pedidoAlterado = pedidoService.alterarPedido(id, pedidoDto);
		if (!pedidoAlterado.isPresent()) {
			return ResponseEntity.status(403).body("Erro");
		}
		return ResponseEntity.ok(pedidoAlterado.get());
	}
}
