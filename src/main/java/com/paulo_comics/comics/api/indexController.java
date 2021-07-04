package com.paulo_comics.comics.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class indexController {
	
	@GetMapping()
	public String get() {
		return "GET Spring Boot";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "Login: " + login + ", Senha: " + senha;
	}
	
	@GetMapping("/usuarios/{id}")
	public String getUsuarioById(@PathVariable("id") Long id) {
		return "Usuário: " + id;
	}
}
