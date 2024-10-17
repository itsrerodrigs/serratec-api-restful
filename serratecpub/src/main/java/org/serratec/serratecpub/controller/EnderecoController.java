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
    public ResponseEntity<String> excluirEndereco(@PathVariable Long id){
        if (! enderecoService.excluirEndereco(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        return ResponseEntity.ok("O endereco foi excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> alterarEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto){
        Optional<EnderecoDto> enderecoAlterado = enderecoService.alterarEndereco(id, enderecoDto);
        if (!enderecoAlterado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoAlterado.get());
    }


}
