package com.paulo_comics.comics.domain.marvel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "http://gateway.marvel.com/v1/public/comics/" , name = "marvelApi")
public interface MarvelInterface {
	
	@GetMapping("{comicId}?ts={ts}&apikey={chaveMarvel}&hash={hashMarvel}")
	MarvelData findComicById(@PathVariable("comicId") Long comicId, @PathVariable("chaveMarvel") String chaveMarvel, @PathVariable("ts") Long ts, @PathVariable("hashMarvel") String hashMarvel);
}
