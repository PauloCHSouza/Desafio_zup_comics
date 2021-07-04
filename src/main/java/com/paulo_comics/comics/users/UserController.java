package com.paulo_comics.comics.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping()
	public Iterable<User> get() {
		return service.getUsers();
	}
	
	@GetMapping("/{id}")
	public Optional<User> get(@PathVariable("id") Long id) {
		return service.getUsersById(id);
	}
	
	@GetMapping("/nome/{nome}")
	public Iterable<User> getUsersByNome(@PathVariable("nome") String nome) {
		return service.getUsersByNome(nome);
	}
	
	@GetMapping("/cpf/{cpf}")
	public Iterable<User> getUsersByCpf(@PathVariable("cpf") String cpf) {
		return service.getUsersByCpf(cpf);
	}
	
	@PostMapping
	public String post(@RequestBody User user) {
		User u = service.save(user);
		
		return "Usuário salvo com sucesso! " + u.getUsuarioId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id,  @RequestBody User user) {
		User u = service.update(user, id);
		
		return "Usuário atualizada com sucesso! " + u.getUsuarioId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Usuário deletado com sucesso!";
	}
}
