package com.paulo_comics.comics.domain.marvel;

import java.util.List;

public class Marvel {
	
	private Long id;
	private String title;
	private String Description;
	private String isbn;
	private List<MarvelPrice> prices;
	private MarvelCreators creators;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<MarvelPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<MarvelPrice> prices) {
		this.prices = prices;
	}
	public MarvelCreators getCreators() {
		return creators;
	}
	public void setCreators(MarvelCreators creators) {
		this.creators = creators;
	}
}