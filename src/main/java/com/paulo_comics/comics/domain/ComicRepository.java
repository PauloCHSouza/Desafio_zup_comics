package com.paulo_comics.comics.domain;

import org.springframework.data.repository.CrudRepository;

public interface ComicRepository extends CrudRepository<Comic, Long>{

	Iterable<Comic> findByTitulo(String titulo);

	Iterable<Comic> findByAutores(String autores);
	
}
