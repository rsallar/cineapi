package com.github.rsallar.cineapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rsallar.cineapi.repository.CinemaRepository;
import com.github.rsallar.cineapi.scraping.Avui;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CinemaLoadingService {

	@Autowired
	Avui avui;
	@Autowired
	CinemaRepository cinemaRepository;

	public void load() {

		avui.getInfo().ifPresent(o -> {
			cinemaRepository.deleteAll();
			cinemaRepository.save(o);
			log.info("cinema info saved");
		});

	}
	
}
