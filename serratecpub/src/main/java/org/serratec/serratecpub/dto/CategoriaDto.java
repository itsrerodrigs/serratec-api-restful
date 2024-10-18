package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.Categoria;
import org.serratec.serratecpub.model.CategoriaNome;

public record CategoriaDto(
		Long id,
		CategoriaNome nome,
		String descricao
		) {

		public Categoria toEntity() {
			Categoria categoria = new Categoria();
			categoria.setId(this.id);
			categoria.setCategoriaNome(this.nome);
			categoria.setDescricao(this.descricao);
			return categoria;
		}
			
		public static CategoriaDto toDto(Categoria categoria) {
			return new CategoriaDto(categoria.getId(), categoria.getCategoriaNome(), categoria.getDescricao());
		}
}
