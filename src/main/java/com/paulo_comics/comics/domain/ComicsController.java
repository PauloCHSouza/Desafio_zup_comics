package com.paulo_comics.comics.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicsController {
	@Autowired
	private ComicService service;
	
	@GetMapping()
	public Iterable<Comic> get() {
		return service.getComics();
	}
	
	@GetMapping("/{id}")
	public Optional<Comic> get(@PathVariable("id") Long id) {
		return service.getComicsById(id);
	}
	
	@GetMapping("/titulo/{titulo}")
	public Iterable<Comic> getComicsByTitulo(@PathVariable("titulo") String titulo) {
		return service.getComicsByTitulo(titulo);
	}
	
	@GetMapping("/autores/{autores}")
	public Iterable<Comic> getComicsByAutor(@PathVariable("autores") String autores) {
		return service.getComicsByAutor(autores);
	}
	
	@PostMapping
	public String post(@RequestBody Comic comic) {
		Comic c = service.save(comic);
		
		return "Comic salva com sucesso! " + c.getComicId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id,  @RequestBody Comic comic) {
		Comic c = service.update(comic, id);
		
		return "Comic atualizada com sucesso! " + c.getComicId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Comic deletada com sucesso!";
	}
}
