package org.serratec.serratecpub.model;

import java.util.List;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 3, max = 50, message = TratamentoDeErro.SizeMessage)
	@Enumerated(EnumType.STRING)
	private CategoriaNome categoriaNome;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(max = 150, message = TratamentoDeErro.SizeMessage)
	private String descricao;
	
	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;
	
	public Long getId() {
		return id;
	}
	public CategoriaNome getCategoriaNome() {
		return categoriaNome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCategoriaNome(CategoriaNome categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
