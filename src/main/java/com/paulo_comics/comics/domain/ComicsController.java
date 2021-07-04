package com.paulo_comics.comics.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
