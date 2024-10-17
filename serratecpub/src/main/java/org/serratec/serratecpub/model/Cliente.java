package org.serratec.serratecpub.model;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.CascadeType;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 5, max = 100)
	private String email;
	private String nomeCompleto;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	
	@OneToOne(cascade= CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos;

	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
