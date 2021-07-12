package com.paulo_comics.comics.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/buscar")
	public List<User> get() {
		return service.getUsers();
	}
	
	@GetMapping("/buscar/{id}")
	public Optional<User> get(@PathVariable("id") Long id) {		
		return service.getUsersById(id);
	}
	
	@PostMapping("/incluir")
	public User post(@RequestBody User user) {
		return service.save(user);
	}
}
