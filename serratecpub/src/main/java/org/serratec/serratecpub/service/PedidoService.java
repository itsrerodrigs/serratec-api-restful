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
	private PedidoRepository repositorio;
	
	public List<PedidoDto> obterTodos(){
		return repositorio.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}
	
	public Optional<PedidoDto> obterPorId(Long id){
		if(!repositorio.existsById(id)) {
			return Optional.empty();	
			}
		return Optional.of(PedidoDto.toDto(repositorio.findById(id).get()));
	}
	public PedidoDto salvarPedido(PedidoDto dto) {
		Pedido pedidoEntity = repositorio.save(dto.toEntity());
		return PedidoDto.toDto(pedidoEntity);
	}
	public boolean apagarPedido(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto dto){
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Pedido pedidoEntity = dto.toEntity();
		pedidoEntity.setId(id);
		repositorio.save(pedidoEntity);
		return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
}
