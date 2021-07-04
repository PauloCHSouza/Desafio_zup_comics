package com.paulo_comics.comics.users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	Iterable<User> findByNome(String nome);

	Iterable<User> findByCpf(String cpf);

}
