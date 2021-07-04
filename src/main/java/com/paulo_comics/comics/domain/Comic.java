package com.paulo_comics.comics.domain;

import javax.persistence.*;

@Entity(name = "comics")
@Table(name = "comics")
public class Comic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(
            name = "comicId",
            updatable = false
    )
	private Long comicId;
	
	@Column(
            name = "usuarioId",
            updatable = false,
            columnDefinition = "int"
    )
	private Long usuarioId;
	
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
            columnDefinition = "varchar(100)"
    )
	private String autores;
	
	@Column(
            name = "preco",
            nullable = false,
            columnDefinition = "decimal"
    )
	private Double preco;
	
	@Column(
            name = "isbn",
            nullable = false,
            columnDefinition = "int"
    )
	private Long isbn;
	
	public Comic() {
		
	}
	 
	public Comic(Long comicId, Long usuarioId, String titulo, Double preco, String autores, Long isbn, String descricao) {
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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	
	
}
