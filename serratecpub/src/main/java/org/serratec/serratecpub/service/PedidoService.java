package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;


import org.serratec.serratecpub.dto.PedidoDto;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepositoriy;
	
	public List<PedidoDto> obterTodosPedidos(){
		return pedidoRepositoriy.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}
	
	public Optional<PedidoDto> obterPedidosPorId(Long id){
		if(!pedidoRepositoriy.existsById(id)) {
			return Optional.empty();	
			}
		return Optional.of(PedidoDto.toDto(pedidoRepositoriy.findById(id).get()));
	}
	public PedidoDto salvarPedido(PedidoDto pedidoDto) {
		Pedido pedidoEntity = pedidoRepositoriy.save(pedidoDto.toEntity());
		return PedidoDto.toDto(pedidoEntity);
	}
	public boolean apagarPedido(Long id) {
		if(!pedidoRepositoriy.existsById(id)) {
			return false;
		}
		pedidoRepositoriy.deleteById(id);
		return true;
	}
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedidoDto){
		if(!pedidoRepositoriy.existsById(id)) {
			return Optional.empty();
		}
		Pedido pedidoEntity = pedidoDto.toEntity();
		pedidoEntity.setId(id);
		pedidoRepositoriy.save(pedidoEntity);
		return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
}
