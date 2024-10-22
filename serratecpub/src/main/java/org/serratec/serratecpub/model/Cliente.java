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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	private String nome;
	
	@Email
	@NotBlank(message = TratamentoDeErro.NotBlankMessage)
	@Size(min = 5, max = 100)
	private String email;
	
	@Size(min=11,max=11, message= TratamentoDeErro.SizeMessage)
	private String cpf;
	
	@NotBlank(message = TratamentoDeErro.NotBlankMessage) 
	@Size(min=11,max=11, message="Telefone deve conter entre 10 e 11 digitos")
	private String telefone;
	
	@PastOrPresent(message = "A data de nascimento deve ser informada no passado ou presente no formato AAAA-MM-DD")
	private LocalDate dataNascimento;

	@OneToOne(cascade = CascadeType.ALL)
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

	@Override
	public String toString() {
		return "Cliente id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", telefone=" + telefone
				+ ", dataNascimento=" + dataNascimento + ", endereco=" + endereco ;
	}	
}
