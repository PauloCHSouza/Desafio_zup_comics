package com.paulo_comics.comics.users;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "usuarios")
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_usuarios_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_usuarios_cpf", columnNames = "cpf")
        }
)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "usuarioId",
            updatable = false
    )
	private Long usuarioId;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
	private String nome;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
	private String email;

    @Column(
            name = "cpf",
            nullable = false,
            columnDefinition = "varchar(11)"
    )
	private String cpf;

    @Column(
            name = "dtNascimento",
            nullable = false,
            columnDefinition = "date"
    )
	private LocalDate dtNascimento;
	
	public User() {
		
	}
	 
	public User(Long usuarioId, String nome, String email, String cpf, LocalDate dtNascimento) {
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	

}
