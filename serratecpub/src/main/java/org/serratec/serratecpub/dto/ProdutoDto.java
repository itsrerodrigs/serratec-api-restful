package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.Categoria;
import org.serratec.serratecpub.model.Produto;

public record ProdutoDto(
		Long id,
		String nome,
		String descricao,
		int qntEstoque,
		Double valorUnitario,
		String imagem,
		Categoria categoria
		) {
		
		public Produto toEntity() {
			Produto produto = new Produto();
			produto.setId(this.id);
			produto.setNome(this.nome);
			produto.setDescricao(this.descricao);
			produto.setQntEstoque(this.qntEstoque);
			produto.setValorUnitario(this.valorUnitario);
			produto.setImagem(this.imagem);
			produto.setCategoria(this.categoria);
			return produto;
		}
			
		public static ProdutoDto toDto(Produto produto) {
			return new ProdutoDto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQntEstoque(),
					produto.getValorUnitario(), produto.getImagem(), produto.getCategoria());
		}
}
