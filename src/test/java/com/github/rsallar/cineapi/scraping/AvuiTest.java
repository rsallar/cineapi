package com.github.rsallar.cineapi.scraping;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.rsallar.cineapi.model.Cinema;
import com.github.rsallar.cineapi.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AvuiTest {

	Avui avui;
	
	@Before
	public void before(){
		avui = new Avui();
	}	

	@Test
	public void test1(){	
		List<Cinema> cinemas = avui.getInfo();
		for(Cinema c: cinemas){
			log.info("#################");
			log.info("cinema: {} ", c.getName());
			for(Movie m: c.getMovies()){
				log.info("movie: {} \nsessions: {} ", m.getName(), m.getSessions());
			}		
			log.info("#################");

		}
		
	}
	

}
