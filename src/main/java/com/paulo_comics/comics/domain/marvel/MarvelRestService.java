package com.paulo_comics.comics.domain.marvel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulo_comics.comics.domain.Comic;
import com.paulo_comics.comics.domain.ComicService;

@RestController
@RequestMapping("/comics")
public class MarvelRestService {
	
	@Autowired
	private MarvelService marvelService;
	
	@Autowired
	private ComicService service;
	
	@GetMapping("/{comicId}/{UsuarioId}")
	public Comic getComic(@PathVariable Long comicId, @PathVariable Long UsuarioId) {	
		
		MarvelData comic = marvelService.buscaComicPorId(comicId);
		
		MarvelResults results =  comic.getData();
		
		List<Marvel> marvel = results.getResults();
		
		Comic comicAdd = new Comic();
		comicAdd.setComicId(comicId);
		comicAdd.setUsuarioId(UsuarioId);
		
		for (Marvel valores : marvel) { 
			comicAdd.setTitulo(valores.getTitle());
			comicAdd.setIsbn(valores.getIsbn());
			comicAdd.setDescricao(valores.getDescription());
			
			List<MarvelPrice> preco = valores.getPrices();
			for (MarvelPrice precos : preco) { 
				if (precos.getType().equals("printPrice")) {
					comicAdd.setPreco(precos.getPrice());
				}
			}
			
			MarvelCreators creators =  valores.getCreators();
			
			String autoresList = "";
			List<MarvelItems> items = creators.getItems();
			for (MarvelItems autores : items) { 
				if (!autoresList.isBlank()) autoresList = autoresList + ", ";
				autoresList = autoresList + autores.getName();
			}

			comicAdd.setAutores(autoresList);

		}
		return comicAdd;
	}
	
	@PostMapping("/incluir/{comicId}/{UsuarioId}")
	public Comic postComic(@PathVariable Long comicId, @PathVariable Long UsuarioId) {	
		
		MarvelData comic = marvelService.buscaComicPorId(comicId);
		
		MarvelResults results =  comic.getData();
		
		List<Marvel> marvel = results.getResults();
		
		Comic comicAdd = new Comic();
		comicAdd.setComicId(comicId);
		comicAdd.setUsuarioId(UsuarioId);
		
		for (Marvel valores : marvel) { 
			comicAdd.setTitulo(valores.getTitle());
			comicAdd.setIsbn(valores.getIsbn());
			comicAdd.setDescricao(valores.getDescription());
			
			List<MarvelPrice> preco = valores.getPrices();
			for (MarvelPrice precos : preco) { 
				if (precos.getType().equals("printPrice")) {
					comicAdd.setPreco(precos.getPrice());
				}
			}
			
			MarvelCreators creators =  valores.getCreators();
			
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
