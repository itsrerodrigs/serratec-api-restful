package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.service.PedidoItemService;
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
@RequestMapping("/itempedido")
public class PedidoItemController {
	@Autowired
	private PedidoItemService servico;
	
	@GetMapping
	public List<ItemPedidoDto> obterTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> obterPorId(@PathVariable Long id) {
		Optional<ItemPedidoDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemPedidoDto cadastrarPedido(@RequestBody ItemPedidoDto dto) {
		return servico.salvarItemPedido(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaPedido(@PathVariable Long id) {
		if (!servico.apagarItemPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> alterarPedido(@PathVariable Long id, @RequestBody ItemPedidoDto dto) {
		Optional<ItemPedidoDto> itemPedidoAlterado = servico.alterarItemPedido(id, dto);
		if (!itemPedidoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedidoAlterado.get());
	}
}
