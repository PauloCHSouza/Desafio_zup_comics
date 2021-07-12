package com.paulo_comics.comics.domain.marvel;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulo_comics.comics.domain.Comic;
import com.paulo_comics.comics.domain.ComicService;
import com.paulo_comics.comics.users.UserService;

@RestController
@RequestMapping("/api/v1/comics")
public class MarvelController {
	
	@Autowired
	private MarvelInterface marvelService;
	
	@Autowired
	private ComicService service;

	@Autowired
	private UserService userService;
	
	@Value("${chaveMarvelPublic}")
	private String chaveMarvelPublic;
	
	@Value("${chaveMarvelPrivate}")
	private String chaveMarvelPrivate;
	
	long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
	
	
	@PostMapping("/incluir/{comicId}/{UsuarioId}")
	public Comic postComic(@PathVariable Long comicId, @PathVariable Long UsuarioId) {	
		
		MarvelData comic = marvelService.findComicById(comicId, chaveMarvelPublic, timestamp, variavelCriaHash());
		
		MarvelResults results =  comic.getData();
		
		List<Marvel> marvel = results.getResults();
		
		Comic comicAdd = new Comic();
		for (Marvel values : marvel) { 
			comicAdd.setComicId(values.getId());
			comicAdd.setUsuarioId(userService.getUsersObById(UsuarioId));
			comicAdd.setTitulo(values.getTitle().isEmpty() ? "" : values.getTitle());
			comicAdd.setIsbn(values.getIsbn().isEmpty() ? "" : values.getIsbn());
			comicAdd.setDescricao(values.getDescription() == null ? "" : values.getDescription());
			if (!values.getIsbn().isEmpty()) {
				comicAdd.setDiaDesconto(Integer.parseInt(values.getIsbn().substring((values.getIsbn().length())-1, values.getIsbn().length())));
			}else {
				comicAdd.setDiaDesconto(0);
			}
			
			List<MarvelPrice> price = values.getPrices();
			
			if (!price.isEmpty()) {
				for (MarvelPrice prices : price) { 
					if (prices.getType().equals("printPrice")) {
						comicAdd.setPreco(prices.getPrice());
					}
				}
			}
			
			MarvelCreators creators =  values.getCreators();
			
			if (!creators.equals("") && !(creators == null)) {
				String autoresList = "";
				List<MarvelItems> items = creators.getItems();
				for (MarvelItems autores : items) { 
					if (autores.getRole().equals("writer")) {
						if (!autoresList.isBlank()) autoresList = autoresList + ", ";
						autoresList = autoresList + autores.getName();
					}
				}
				comicAdd.setAutores(autoresList);
			}

		}
		return service.save(comicAdd);
	}
	
	private String getMD5Hash(String data) {
        try {            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
              .printHexBinary(digest).toLowerCase();
            
            return myHash;
                
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }
	
	public String variavelCriaHash() {
		return getMD5Hash(timestamp+chaveMarvelPrivate+chaveMarvelPublic);
	}
	
}
