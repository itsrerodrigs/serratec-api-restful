package org.serratec.serratecpub.util;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.model.Cliente;
import org.serratec.serratecpub.model.Endereco;
import org.serratec.serratecpub.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoUtil {
    @Autowired
    private ViaCepService viaCepService;

    public void processarEndereco(ClienteDto clienteDto, Cliente clienteEntity) {
        if (clienteDto.endereco() != null) {
            Endereco endereco = viaCepService.preencherEnderecoComCep(clienteDto.endereco().cep());
            if (endereco != null) {
                endereco.setNumero(clienteDto.endereco().numero());
                endereco.setComplemento(clienteDto.endereco().complemento());
                clienteEntity.setEndereco(endereco);
            } else {
                throw new IllegalArgumentException("CEP inválido ou sem retorno de dados");
            }
        }
    }
}