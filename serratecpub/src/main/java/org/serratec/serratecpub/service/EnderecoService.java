package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.EnderecoDto;
import org.serratec.serratecpub.model.Endereco;
import org.serratec.serratecpub.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService{
    @Autowired
    
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDto> obterTodosEnderecos(){
        return enderecoRepository.findAll().stream().map(e -> EnderecoDto.toDto(e)).toList();
    }
 
    public Optional<EnderecoDto> obterEnderecoPorId(Long id){
        if (enderecoRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(EnderecoDto.toDto(enderecoRepository.findById(id).get()));
    }

    public EnderecoDto salvarEndereco(EnderecoDto enderecoDto){
        Endereco enderecoEntity = enderecoRepository.save(enderecoDto.toEntity());
        return EnderecoDto.toDto(enderecoEntity);
    }

    public boolean excluirEndereco(Long id){
        if (!enderecoRepository.existsById(id)){
            return false;
        }
        enderecoRepository.deleteById(id);
        return true;
    }
    
    public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto enderecoDto){
     if (!enderecoRepository.existsById(id)){
        return Optional.empty();
     }   
     Endereco enderecoEntity = enderecoDto.toEntity();
     enderecoEntity.setId(id);
     enderecoRepository.save(enderecoEntity);
     return Optional.of(EnderecoDto.toDto(enderecoEntity));
    }



}