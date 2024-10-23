package org.serratec.serratecpub.controller;

import java.util.Optional;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired

    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Retornar lista de clientes", description = "Dado um determinado id, será retornado o cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente localizado!"), 
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"), 
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado!")})
    public ResponseEntity<?> listarClientes() {
		if(clienteService.obterTodosClientes().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cliente cadastrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterTodosClientes());
	}

    @GetMapping("/{id}")
    @Operation(summary = "Retornar cliente pelo id", description = "Dado um determinado id, será retornado o cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente localizado!"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    public ResponseEntity<?> listarClientePorId(@PathVariable Long id) {
        Optional<ClienteDto> dto = clienteService.obterClientePorId(id);
        if (!dto.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro! O cliente não foi encontrado");
		}
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Retornar cliente pelo id", description = "Dado um determinado id, será retornado o cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastra!"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ClienteDto cadastrarCliente(@RequestBody @Valid ClienteDto clienteDto){
        return clienteService.salvarCliente(clienteDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente pelo id", description = "Dado um determinado id, será excluído o cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente excluído!"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia, é porque não existe cliente com esse id. Verifique!"),
			})
    public ResponseEntity<String> excluirCliente(@PathVariable Long id){
        if(!clienteService.excluirCliente(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.ok("Cliente com id " + id + " excluído com sucesso!");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar cliente pelo id", description = "Dado um determinado id, será alterado o cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "200", description = "Cliente alterado!"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso!"),
   			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia, é porque não existe cliente com esse id. Verifique!"),
   			})
    public ResponseEntity<?> alterarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteDto> clienteAlterado = clienteService.alterarCliente(id, clienteDto);
        if (!clienteAlterado.isPresent()){
            return ResponseEntity.status(403).body("O cliente não foi alterado!");
        }
        return ResponseEntity.ok(clienteAlterado.get());
    }
}