package com.paulo_comics.comics.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.paulo_comics.comics.users.User;

@Entity(name = "comics")
@Table(name = "comics")
public class Comic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(
            name = "Id",
            updatable = false
    )
	private Long Id;
	
	@Column(
            name = "comicId",
            updatable = false
    )
	private Long comicId;
	

	@ManyToOne
    @JoinColumn(name = "usuarioId")
	private User usuarioId;
	
	@Column(
            name = "titulo",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
	private String titulo;
	
	@Column(
            name = "descricao",
            nullable = false,
            columnDefinition = "longtext"
    )
	private String descricao;
	
	@Column(
            name = "autores",
            nullable = false,
            columnDefinition = "longtext"
    )
	private String autores;
	
	@Column(
            name = "preco",
            nullable = false,
            columnDefinition = "double"
    )
	private Double preco;
	
	@Column(
            name = "isbn",
            nullable = false,
            columnDefinition = "varchar(25)"
    )
	private String isbn;
	
	@Column(
            name = "diaDesconto",
            nullable = false,
            columnDefinition = "int"
    )
	private Integer diaDesconto;
	
	@Transient
	private Boolean descontoAtivo;

	public Comic() {
		
	}
	 
	public Comic(Long comicId, User usuarioId, String titulo, Double preco, String autores, String isbn, String descricao, Integer diaDesconto, Boolean descontoAtivo) {
		this.comicId = comicId;
		this.usuarioId = usuarioId;
		this.titulo = titulo;
		this.preco = preco;
		this.autores = autores;
		this.isbn = isbn;
		this.descricao = descricao;
	}
	
	public Long getComicId() {
		return comicId;
	}

	public void setComicId(Long comicId) {
		this.comicId = comicId;
	}

	public User getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(User optional) {
		this.usuarioId = optional;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public Double getPreco() {
		if (getDescontoAtivo()) {
			return preco-(preco*0.1);
		}else {
			return preco;
		}
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Integer getDiaDesconto() {
		return diaDesconto;
	}

	public void setDiaDesconto(Integer diaDesconto) {
		this.diaDesconto = diaDesconto;
	}

	public Boolean getDescontoAtivo() {
		return  validaDesconto(diaDesconto, verificaDiaSemana());
	}
	
	public Integer verificaDiaSemana() {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	public Boolean validaDesconto(Integer diaDesconto, Integer diaSemana) {
		
		if ((diaDesconto.equals(0) || diaDesconto.equals(1)) && diaSemana.equals(2)) {
			return true;
		}else if ((diaDesconto.equals(2) || diaDesconto.equals(3)) && diaSemana.equals(3)) {
			return true;
		}else if ((diaDesconto.equals(4) || diaDesconto.equals(5)) && diaSemana.equals(4)) {
			return true;
		}else if ((diaDesconto.equals(6) || diaDesconto.equals(7)) && diaSemana.equals(5)) {
			return true;
		}else if ((diaDesconto.equals(8) || diaDesconto.equals(9)) && diaSemana.equals(6)) {
			return true;
		}else {
			return false;
		}
	}
	
}
