package com.paulo_comics.comics.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByCpf(String cpf);

	Optional<User> findByEmail(String email);
	
}
