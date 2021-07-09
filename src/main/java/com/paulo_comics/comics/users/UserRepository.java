package com.paulo_comics.comics.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByCpf(String cpf);

	Optional<User> findByEmail(String email);

	@Query("select u from usuarios u where u.usuarioId = ?1")
	User findByIdOb(Long id);
	
}
