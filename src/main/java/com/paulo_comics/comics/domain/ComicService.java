package com.paulo_comics.comics.domain;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paulo_comics.comics.users.User;

@Service
public class ComicService {

	@Autowired
	private ComicRepository rep;

	public List<Comic> getComics() {

		List<Comic> c = rep.findAll();

		if (c.isEmpty() || c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro encontrado.");
		} else {
			return c;
		}
	}

	public Optional<Comic> getComicsById(Long id) {
		Optional<Comic> c = rep.findById(id);
		
		if (c.isEmpty() || c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro encontrado com o ID: " + id);
		} else {
			return c;
		}
	}
	
	public List<Comic> getComicsByUser(User user) {
		List<Comic> c = rep.findByUser(user);
		if (c.isEmpty() || c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma comic encontrada no usuário informado!");
		} else {
			return c;
		}
	}	


	public Optional<Comic> getComicsByIdAndUser(Long id, User user) {
		Optional<Comic> c = rep.findByIdAndUser(id, user);
		return c;
	}

	public Comic save(Comic comic) {
		
		if (comic == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foram informados dados da Comic");
		
		if (comic.getTitulo().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Titulo não informado!");
		
		if (comic.getPreco().isNaN() || comic.getPreco() <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preço não informado ou inválido!");
		
		if (comic.getAutores().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor não informado!");
		
		if (comic.getIsbn().length() < 10) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN não informado ou inválido!");
		
		if (comic.getDescricao().isEmpty() || comic.getDescricao() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição não informada!");

		if (comic.getUsuarioId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não informado!");
		
		if (!getComicsByIdAndUser(comic.getComicId(), comic.getUsuarioId()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A Comic informada já está vinculada com este usuário!");	
		
		rep.save(comic);
		
		throw new ResponseStatusException(HttpStatus.CREATED, "Comic cadastrada com sucesso!");
		
	}

	public Comic delete(Long id) {
		Optional<Comic> optional = getComicsById(id);
		if (optional.isPresent()) {
			rep.deleteById(id);
			throw new ResponseStatusException(HttpStatus.OK, "Comic excluída com sucesso!");
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel localizar o registro de ID: " + id);
		}
	}
	
	public Integer verificaDiaSemana() {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
}
