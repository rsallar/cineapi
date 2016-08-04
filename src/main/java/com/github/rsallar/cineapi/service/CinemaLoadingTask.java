package com.github.rsallar.cineapi.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CinemaLoadingTask {

	@Autowired CinemaLoadingService service;
	
	@Scheduled(cron="0 0/1 4,16 * * ?")
	public void searchNewInfo() {
		log.info("Cron Job: Reloading cinemas from web");
		service.load();
	}

	@PostConstruct
	public void initial(){
		log.info("Initial Job: Getting cinemas from web");
		service.load();
	}

}
