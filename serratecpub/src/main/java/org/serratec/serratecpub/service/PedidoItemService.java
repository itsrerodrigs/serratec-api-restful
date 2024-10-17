package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class PedidoItemService {

		@Autowired
		private ItemPedidoRepository repositorio;
		
		public List<ItemPedidoDto> obterTodos(){
			return repositorio.findAll().stream().map(p -> ItemPedidoDto.toDto(p)).toList();
		}
		
		public Optional<ItemPedidoDto> obterPorId(Long id){
			if(!repositorio.existsById(id)) {
				return Optional.empty();	
				}
			return Optional.of(ItemPedidoDto.toDto(repositorio.findById(id).get()));
		}
		
		public ItemPedidoDto salvarItemPedido(ItemPedidoDto dto) {
	        ItemPedido itemPedidoEntity = dto.toEntity();
	        itemPedidoEntity.setValorBruto(dto.calcularValorBruto());
	        itemPedidoEntity.setValorDesconto(dto.calcularValorDesconto());
	        itemPedidoEntity.setValorLiquido(dto.calcularValorLiquido());
	        ItemPedido itemPedidoSalvo = repositorio.save(itemPedidoEntity);
	        return ItemPedidoDto.toDto(itemPedidoSalvo);
	    }
		
		public boolean apagarItemPedido(Long id) {
			if(!repositorio.existsById(id)) {
				return false;
			}
			repositorio.deleteById(id);
			return true;
		}
		public Optional<ItemPedidoDto> alterarItemPedido(Long id, ItemPedidoDto dto){
			if(!repositorio.existsById(id)) {
				return Optional.empty();
			}
			ItemPedido itemPedidoEntity = dto.toEntity();
			itemPedidoEntity.setId(id);
			repositorio.save(itemPedidoEntity);
			return Optional.of(ItemPedidoDto.toDto(itemPedidoEntity));
		}
}
