package com.github.rsallar.cineapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.rsallar.cineapi.model.Cinema;
import com.github.rsallar.cineapi.service.CinemaLoadingService;
import com.github.rsallar.cineapi.service.CinemaService;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
	
	@Autowired CinemaService service;
	@Autowired CinemaLoadingService loadService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cinema> getCinemas(){
		return service.getCinemas();
	}
	
	@RequestMapping(value="/load", method = RequestMethod.GET)
	public void loadCinemas(){
		loadService.load();
	}

}
