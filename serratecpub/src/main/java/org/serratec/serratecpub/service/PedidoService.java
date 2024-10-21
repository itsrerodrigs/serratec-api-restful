package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.PedidoDto;
import org.serratec.serratecpub.dto.RelatorioPedidoDto;
import org.serratec.serratecpub.model.Endereco;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.Produto;
import org.serratec.serratecpub.model.ViaCepService;
import org.serratec.serratecpub.repository.EnderecoRepository;
import org.serratec.serratecpub.repository.PedidoRepository;
import org.serratec.serratecpub.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ViaCepService viaCepService;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<PedidoDto> obterTodosPedidos() {
		return pedidoRepository.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}

	public Optional<PedidoDto> obterPedidosPorId(Long id) {
		if (!pedidoRepository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(pedidoRepository.findById(id).get()));
	}

	public List<PedidoDto> obterPedidosPorNomeCliente(String nome) {
		return pedidoRepository.BuscarPedidoPorNomeCliente(nome).stream().map(PedidoDto::toDto).toList();
	}
	
	public Optional<RelatorioPedidoDto> obterRelatorioPedido(Long id) {
		  Optional<Pedido> pedido = pedidoRepository.findById(id);
		  if (pedido.isPresent()) {
		    return Optional.of(RelatorioPedidoDto.toDto(pedido.get()));
		  }
		  return Optional.empty();
		}


	public PedidoDto salvarPedido(PedidoDto pedidoDto) {
		Pedido pedidoEntity = pedidoDto.toEntity();
			//verifica o endereco do cliente
		if (pedidoDto.cliente().getEndereco() != null) {
			// pega o cep do endereço e chama o metodo viacep para buscar as informações e preencher
			Endereco endereco = viaCepService.preencherEnderecoComCep(pedidoDto.cliente().getEndereco().getCep());
			if (endereco != null) {
				endereco.setNumero(pedidoDto.cliente().getEndereco().getNumero());
				endereco.setComplemento(pedidoDto.cliente().getEndereco().getComplemento());
				endereco = enderecoRepository.save(endereco);
				pedidoDto.cliente().setEndereco(endereco);
			} else {
				throw new IllegalArgumentException("CEP inválido ou sem retorno de dados");
			}
		}
		for (ItemPedido item : pedidoEntity.getItemPedido()) {
	        Produto produtoVerificado = verificarProduto(item.getProduto());
	        item.setProduto(produtoVerificado); // Associar o produto verificado ao item
	    }
		
		pedidoEntity = pedidoRepository.save(pedidoEntity);
		return PedidoDto.toDto(pedidoEntity);

	}
	public Produto verificarProduto(Produto produto) {
        Optional<Produto> produtoExistente = produtoRepository.findByNomeIgnoreCase(produto.getNome());

        if (produtoExistente.isPresent()) {
            return produtoExistente.get();
        }

        Produto novoProduto = produto;
        return produtoRepository.save(novoProduto);
    }
	public boolean apagarPedido(Long id) {
		if (!pedidoRepository.existsById(id)) {
			return false;
		}
		pedidoRepository.deleteById(id);
		return true;
	}

	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedidoDto) {
		if (!pedidoRepository.existsById(id)) {
			return Optional.empty();
		}
		Pedido pedidoEntity = pedidoDto.toEntity();
		pedidoEntity.setId(id);
		pedidoRepository.save(pedidoEntity);
		return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
}
