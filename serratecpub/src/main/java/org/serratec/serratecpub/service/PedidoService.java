
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.serratec.serratecpub.dto.ItemPedidoDto;
import org.serratec.serratecpub.dto.PedidoDto;
import org.serratec.serratecpub.dto.ProdutoDto;
import org.serratec.serratecpub.model.ItemPedido;
import org.serratec.serratecpub.model.Pedido;
import org.serratec.serratecpub.model.Produto;
import org.serratec.serratecpub.repository.PedidoRepository;
import org.serratec.serratecpub.repository.ProdutoRepository;
import org.serratec.serratecpub.util.EnderecoUtil;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnderecoUtil enderecoUtil;

    public PedidoDto salvarPedido(PedidoDto pedidoDto) {
        Pedido pedidoEntity = pedidoDto.toEntity();
        enderecoUtil.processarEndereco(pedidoDto.cliente(), pedidoEntity.getCliente());
        for (ItemPedido item : pedidoEntity.getItemPedido()) {
            Produto produtoVerificado = verificarProduto(item.getProduto());
            item.setProduto(produtoVerificado);
        }
        pedidoEntity = pedidoRepository.save(pedidoEntity);
        return PedidoDto.toDto(pedidoEntity);
    }

    public Produto verificarProduto(ProdutoDto produtoDto) {
        Optional<Produto> produtoExistente = produtoRepository.findByNomeIgnoreCase(produtoDto.nome());
        if (produtoExistente.isPresent()) {
            return produtoExistente.get();
        }

        Produto novoProduto = produtoDto.toEntity();
        return produtoRepository.save(novoProduto);
    }

    public Optional<PedidoDto> alterarPedido(Long id, PedidoDto pedidoDto) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoDto.toEntity();
            pedido.setId(id);
            pedidoRepository.save(pedido);
            return Optional.of(PedidoDto.toDto(pedido));
        }
        return Optional.empty();
    }

    public boolean apagarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
