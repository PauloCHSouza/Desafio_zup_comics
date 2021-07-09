package com.paulo_comics.comics.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.paulo_comics.comics.users.UserService;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicsController {
	@Autowired
	private ComicService service;

	@Autowired
	private UserService userService;
	
	@GetMapping("/buscar")
	public List<Comic> get() {
		return service.getComics();
	}
	
	@GetMapping("/dia")
	public Integer getDia() {
		return service.verificaDiaSemana();
	}

	@GetMapping("/buscar/{usuarioId}")
	public List<Comic> get(@PathVariable("usuarioId") Long usuarioId) {
		return service.getComicsByUser(userService.getUsersObById(usuarioId));

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
