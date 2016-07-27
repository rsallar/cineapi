package com.github.rsallar.cineapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rsallar.cineapi.scraping.ElPais;

@RestController
@RequestMapping("cinemas")
public class CinemaController {
	
	@Autowired
	ElPais elPais;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public List<Cinema> getCinemas(){
		return elPais.getCinemas();
	}*/

}
