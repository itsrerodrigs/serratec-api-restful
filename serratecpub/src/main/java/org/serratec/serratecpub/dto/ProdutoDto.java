package org.serratec.serratecpub.dto;

import java.time.LocalDate;

import org.serratec.serratecpub.model.CategoriaNome;
import org.serratec.serratecpub.model.Produto;

public record ProdutoDto(Long id,
		String nome,
		CategoriaNome categoria,
		String descricao,
		LocalDate dataCadastro,
		int qtdEstoque,
		Double valorUnitario
		) {

	public Produto toEntity() {
		Produto produto = new Produto();
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setCategoria(CategoriaNome.valueOf(this.categoria.name()));
		produto.setDescricao(this.descricao);
		produto.setDataCadastro(this.dataCadastro);
		produto.setQtdEstoque(this.qtdEstoque);
		produto.setValorUnitario(this.valorUnitario);
		return produto;
	}

	public static ProdutoDto toDto(Produto produto) {
		return new ProdutoDto(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getDescricao(),
				produto.getDataCadastro(), produto.getQtdEstoque(), produto.getValorUnitario());
	}
}
