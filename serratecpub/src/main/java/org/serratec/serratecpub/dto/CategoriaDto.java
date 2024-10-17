package org.serratec.serratecpub.dto;

import org.serratec.serratecpub.model.Categoria;

public record CategoriaDto(
		Long id,
		String nome,
		String descricao
		) {
		
		public Categoria toEntity() {
			Categoria categoria = new Categoria();
			categoria.setId(this.id);
			categoria.setNome(this.nome);
			categoria.setDescricao(this.descricao);
			return categoria;
		}
			
		public static CategoriaDto toDto(Categoria categoria) {
			return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao());
		}
}
