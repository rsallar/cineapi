package com.github.rsallar.cineapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.rsallar.cineapi.model.Cinema;
import com.github.rsallar.cineapi.repository.CinemaRepository;

@Service
public class CinemaService {

	@Autowired CinemaRepository repository;
	
	@Cacheable(value = "cinema")
	public List<Cinema> getCinemas(){
		return repository.findAll();
	}
		
}
