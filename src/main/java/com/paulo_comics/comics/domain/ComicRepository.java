package com.paulo_comics.comics.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paulo_comics.comics.users.User;

public interface ComicRepository extends JpaRepository<Comic, Long>{
	
	@Query("select c from comics c where c.comicId = ?1 and c.usuarioId = ?2")
	Optional<Comic> findByIdAndUser(Long id, User user);

	@Query("select c from comics c where c.usuarioId = ?1")
	List<Comic> findByUser(User user);
		
}
