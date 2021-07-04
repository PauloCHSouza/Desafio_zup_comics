package com.paulo_comics.comics.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicService {
	
	@Autowired
	private ComicRepository rep;
	
	public Iterable<Comic> getComics(){
		return rep.findAll();
	}
	
	public List<Comic> getComicsFake(){
		List<Comic> comics = new ArrayList<>();
		
		comics.add(new Comic(1L, "Vingadores", 1.50, "Stan lee", 1111111L, "Apenas um teste"));
		comics.add(new Comic(1L, "Thor", 1.60, "Stan lee", 2222222L, "Apenas um teste 2"));
		comics.add(new Comic(1L, "Sentinela", 1.70, "Stan lee", 3333333L, "Apenas um teste 3"));
		
		return comics;
	}

}
