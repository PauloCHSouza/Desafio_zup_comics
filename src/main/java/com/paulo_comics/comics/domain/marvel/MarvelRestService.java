package com.paulo_comics.comics.domain.marvel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulo_comics.comics.domain.Comic;
import com.paulo_comics.comics.domain.ComicService;
import com.paulo_comics.comics.users.UserService;

@RestController
@RequestMapping("/api/v1/comics")
public class MarvelRestService {
	
	@Autowired
	private MarvelService marvelService;
	
	@Autowired
	private ComicService service;

	@Autowired
	private UserService userService;
	
	@PostMapping("/incluir/{comicId}/{UsuarioId}")
	public Comic postComic(@PathVariable Long comicId, @PathVariable Long UsuarioId) {	
		
		MarvelData comic = marvelService.findComicById(comicId);
		
		MarvelResults results =  comic.getData();
		
		List<Marvel> marvel = results.getResults();
		
		
		Comic comicAdd = new Comic();
		comicAdd.setComicId(comicId);
		comicAdd.setUsuarioId(userService.getUsersObById(UsuarioId));
		
		for (Marvel values : marvel) { 
			comicAdd.setTitulo(values.getTitle());
			comicAdd.setIsbn(values.getIsbn());
			comicAdd.setDescricao(values.getDescription() == null ? "" : values.getDescription());
			comicAdd.setDiaDesconto(Integer.parseInt(values.getIsbn().substring((values.getIsbn().length())-1, values.getIsbn().length())));
			
			List<MarvelPrice> price = values.getPrices();
			for (MarvelPrice prices : price) { 
				if (prices.getType().equals("printPrice")) {
					comicAdd.setPreco(prices.getPrice());
				}
			}
			
			MarvelCreators creators =  values.getCreators();
			
			String autoresList = "";
			List<MarvelItems> items = creators.getItems();
			for (MarvelItems autores : items) { 
				if (!autoresList.isBlank()) autoresList = autoresList + ", ";
				autoresList = autoresList + autores.getName();
			}

			comicAdd.setAutores(autoresList);

		}
		return service.save(comicAdd);
	}

}
