package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.EnderecoDto;
import org.serratec.serratecpub.service.EnderecoService;
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
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

	
	
    @GetMapping
    public List<EnderecoDto> listarEnderecos(){
        return enderecoService.obterTodosEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retornar endereço pelo id", description = "Dado um determinado id, será retronado o endereço do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
   			@ApiResponse(responseCode = "200", description = "Endereço informado!") })
    public ResponseEntity<EnderecoDto> listarEnderecosPorId(@PathVariable Long id){
        Optional<EnderecoDto> enderecoDto = enderecoService.obterEnderecoPorId(id);
        if (enderecoDto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoDto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDto cadastraEndereco(@RequestBody EnderecoDto enderecoDto){	    	
        return enderecoService.salvarEndereco(enderecoDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir endereço pelo id", description = "Dado um determinado id, será excluído o endereço do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
   			@ApiResponse(responseCode = "200", description = "Endereço excluído!") })
    public ResponseEntity<String> excluirEndereco(@PathVariable Long id){
        if (! enderecoService.excluirEndereco(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        return ResponseEntity.ok("O endereco foi excluído com sucesso!");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera endereço pelo id", description = "Dado um determinado id, será alterado o endereço do cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
   			@ApiResponse(responseCode = "200", description = "Endereço alterado!") })
    public ResponseEntity<EnderecoDto> alterarEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto){
        Optional<EnderecoDto> enderecoAlterado = enderecoService.alterarEndereco(id, enderecoDto);
        if (!enderecoAlterado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoAlterado.get());
    }


}
