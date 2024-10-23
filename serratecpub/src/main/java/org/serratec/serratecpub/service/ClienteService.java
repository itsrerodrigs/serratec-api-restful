package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.repository.ClienteRepository;
import org.serratec.serratecpub.util.EnderecoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoUtil enderecoUtil;
    
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
        enderecoUtil.processarEndereco(clienteDto, clienteEntity);
        clienteEntity = clienteRepository.save(clienteEntity);
        return ClienteDto.toDto(clienteEntity);
    }

    public boolean excluirCliente(Long id){
        if (!clienteRepository.existsById(id)){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    public Optional<ClienteDto> alterarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setNome(clienteDto.nome());
            clienteExistente.setEmail(clienteDto.email());
            clienteExistente.setTelefone(clienteDto.telefone());
            enderecoUtil.processarEndereco(clienteDto, clienteExistente);
            clienteRepository.save(clienteExistente);
            return ClienteDto.toDto(clienteExistente);
        });
    }
}