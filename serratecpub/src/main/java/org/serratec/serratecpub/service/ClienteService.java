package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.Endereco;
import org.serratec.serratecpub.model.ViaCepService;
import org.serratec.serratecpub.repository.ClienteRepository;
import org.serratec.serratecpub.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService  viaCepService;
    
    public List<ClienteDto> obterTodosClientes(){
        return clienteRepository.findAll().stream().map(c -> ClienteDto.toDto(c)).toList();
    }

    public Optional<ClienteDto> obterClientePorId(Long id){
        if (!clienteRepository.existsById(id)){
            return Optional.empty();
        }
        return Optional.of(ClienteDto.toDto(clienteRepository.findById(id).get()));
    }
    
   
    public ClienteDto salvarCliente(ClienteDto clienteDto) {
        Cliente clienteEntity = clienteDto.toEntity();
        if (clienteDto.endereco() != null) {
            Endereco endereco = viaCepService.preencherEnderecoComCep(clienteDto.endereco().cep());
            if (endereco != null) {
                endereco.setNumero(clienteDto.endereco().numero());
                endereco.setComplemento(clienteDto.endereco().complemento());
                endereco = enderecoRepository.save(endereco);
                clienteEntity.setEndereco(endereco); 
            } else {
                throw new IllegalArgumentException("CEP inválido ou sem retorno de dados");
            }
        }
        clienteEntity = clienteRepository.save(clienteEntity);
        return ClienteDto.toDto(clienteEntity);
    }
//    public ClienteDto salvarCliente(ClienteDto clienteDto){
//        Cliente clienteEntity = clienteRepository.save(clienteDto.toEntity());
//        return ClienteDto.toDto(clienteEntity);
//    }

    public boolean excluirCliente(Long id){
        if (!clienteRepository.existsById(id)){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    public Optional<ClienteDto> alterarCliente(Long id, ClienteDto clienteDto){
        if (!clienteRepository.existsById(id)){
            return Optional.empty();
        }
        Cliente clienteEntity = clienteDto.toEntity();
        clienteEntity.setId(id);
        clienteRepository.save(clienteEntity);
        return Optional.of(ClienteDto.toDto(clienteEntity));
    }


}