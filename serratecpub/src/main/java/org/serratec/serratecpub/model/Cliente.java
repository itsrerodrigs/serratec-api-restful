package org.serratec.serratecpub.model;

import java.time.LocalDate;

import org.serratec.serratecpub.util.TratamentoDeErro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	private String nome;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 5, max = 100)
	private String email;
	
//	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	//@Size(min=11,max=11, message="Somente numeros no CPF")
	private String cpf;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	private String telefone;
	
	private LocalDate dataNascimento;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nomeCompleto) {
		this.nome = nomeCompleto;
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
}
