package com.paulo_comics.comics.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicsController {
	@Autowired
	private ComicService service;

	@GetMapping("/buscar")
	public List<Comic> get() {
		return service.getComics();
	}

	@GetMapping("/buscar/{id}")
	public Optional<Comic> get(@PathVariable("id") Long id) {
		return service.getComicsById(id);

	}

	@PostMapping("/incluir")
	public Comic post(@RequestBody Comic comic) {
		return service.save(comic);
	}
	
	@PutMapping("/atualizar")
	public Comic put(@RequestBody Comic comic) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum ID informado!");
	}

	@PutMapping("/atualizar/{id}")
	public Comic put(@PathVariable("id") Long id, @RequestBody Comic comic) {
		return service.update(comic, id);
	}
	
	@DeleteMapping("/deletar")
	public Comic delete() {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum ID informado!");
	}

	@DeleteMapping("/deletar/{id}")
	public Comic delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
}
