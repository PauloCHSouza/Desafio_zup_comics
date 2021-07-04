package com.paulo_comics.comics.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ComicService {
	
	@Autowired
	private ComicRepository rep;
	
	public Iterable<Comic> getComics(){
		return rep.findAll();
	}

	public Optional<Comic> getComicsById(Long id) {
		return rep.findById(id);
	}

	public Iterable<Comic> getComicsByTitulo(String titulo) {
		return rep.findByTitulo(titulo);
	}

	public Iterable<Comic> getComicsByAutor(String autores) {
		// TODO Auto-generated method stub
		return rep.findByAutores(autores);
	}

	public Comic save(Comic comic) {		
		return rep.save(comic);
	}

	public Comic update(Comic comic, Long id) {	
		Assert.notNull(id, "Não foi possível atualizar o registro");
		
		Optional<Comic> optional = getComicsById(id);
		if(optional.isPresent()) {
			Comic db = optional.get();
			
			//Copiar as propriedades
			db.setTitulo(comic.getTitulo());
			db.setPreco(comic.getPreco());
			db.setAutores(comic.getAutores());
			db.setIsbn(comic.getIsbn());
			db.setDescricao(comic.getDescricao());
			
			rep.save(db);
			
			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}

	public void delete(Long id) {
		Optional<Comic> optional = getComicsById(id);
		if(optional.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Não foi possivel localizar o registro");
		}
	}
}
