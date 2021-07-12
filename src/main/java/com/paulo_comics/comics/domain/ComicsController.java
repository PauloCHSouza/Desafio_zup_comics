package com.paulo_comics.comics.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/buscar/{usuarioId}")
	public List<Comic> get(@PathVariable("usuarioId") Long usuarioId) {
		return service.getComicsByUser(userService.getUsersObById(usuarioId));

	}
}
