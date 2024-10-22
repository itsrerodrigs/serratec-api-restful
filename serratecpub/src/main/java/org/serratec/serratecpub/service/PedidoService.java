package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.PedidoDto;
import org.serratec.serratecpub.dto.RelatorioPedidoDto;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.Produto;
import org.serratec.serratecpub.repository.PedidoRepository;
import org.serratec.serratecpub.repository.ProdutoRepository;
import org.serratec.serratecpub.util.EnderecoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnderecoUtil enderecoUtil;
    
    @Autowired
    private EmailService email;
    
    
	public List<PedidoDto> obterTodosPedidos() {
        return pedidoRepository.findAll().stream().map(PedidoDto::toDto).toList();
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
        enderecoUtil.processarEndereco(pedidoDto.cliente(), pedidoEntity.getCliente());
        for (ItemPedido item : pedidoEntity.getItemPedido()) {
            Produto produtoVerificado = verificarProduto(item.getProduto());
            item.setProduto(produtoVerificado);
            item.calcularValores();
        }
        pedidoEntity.setValorTotal(pedidoDto.valorTd(pedidoEntity));
        pedidoEntity = pedidoRepository.save(pedidoEntity);
        email.enviarEmail(pedidoEntity.getCliente().getEmail(), "Pedido Realizado com sucesso", pedidoEntity.toString());
        return PedidoDto.toDto(pedidoEntity);
    }

    public Produto verificarProduto(Produto produto) {
        Optional<Produto> produtoExistente = produtoRepository.findByNomeIgnoreCase(produto.getNome());
        if (produtoExistente.isPresent()) {
            return produtoExistente.get();
        }
        return produtoRepository.save(produto);
    }

//    public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedidoDto) {
//        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
//        if (pedidoExistente.isPresent()) {
//            Pedido pedido = pedidoDto.toEntity();
//            pedido.setId(id);
//            pedidoRepository.save(pedido);
//            return Optional.of(PedidoDto.toDto(pedido));
//        }
//        return Optional.empty();
//    }
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedidoDto) {
		return pedidoRepository.findById(id).map(pedidoExistente -> {
			Pedido pedido = pedidoDto.toEntity();
			pedido.setId(id);
			enderecoUtil.processarEndereco(pedidoDto.cliente(), pedido.getCliente());
			for (ItemPedido item : pedido.getItemPedido()) {
				Produto produtoVerificado = verificarProduto(item.getProduto());
				item.setProduto(produtoVerificado);
				item.calcularValores();
			}
			pedido.setValorTotal(pedidoDto.valorTd(pedido));
			pedidoRepository.save(pedido);
			return PedidoDto.toDto(pedido);
		});
	}

    public boolean apagarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}