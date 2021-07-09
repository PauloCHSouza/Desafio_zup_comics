package com.paulo_comics.comics.domain;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComicRepository extends JpaRepository<Comic, Long>{
	
	@Query("select c from comics c where c.comicId = ?1 and c.usuarioId = ?2")
	Optional<Comic> findByIdAndUser(Long id, Long usuarioId);
		
}
