package com.paulo_comics.comics.domain.marvel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "http://gateway.marvel.com/v1/public/comics/" , name = "marvelApi")
public interface MarvelService {
	
	@GetMapping("{comicId}?ts=1&apikey=62199db4913258cd1e56378cf7909922&hash=f73733a4a9c3cfec9c4a8a96442401ab")
	MarvelData buscaComicPorId(@PathVariable("comicId") Long comicId);
}
