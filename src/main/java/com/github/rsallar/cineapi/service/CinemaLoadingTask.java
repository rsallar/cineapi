package com.github.rsallar.cineapi.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.rsallar.cineapi.model.Cinema;
import com.github.rsallar.cineapi.repository.CinemaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class CinemaLoadingTask {

	@Autowired CinemaLoadingService service;
	@Autowired CinemaRepository repo;

	
	@Scheduled(cron="0 0/1 4,16 * * ?")
	@Profile("pro")
	public void searchNewInfo() {
		log.info("Cron Job: Reloading cinemas from web");
		service.load();
	}

	@PostConstruct
	@Profile("default")
	public void initial(){	
		Gson gson = new Gson();
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mocked/cinemas.json");
		String result = new BufferedReader(new InputStreamReader(is)) .lines().collect(Collectors.joining("\n"));
		List<Cinema> cinemas = gson.fromJson(result, new TypeToken<ArrayList<Cinema>>(){}.getType());
		
		repo.save(cinemas);
		log.info("Cinemas Mocked Data Saved");
		
	}

}
