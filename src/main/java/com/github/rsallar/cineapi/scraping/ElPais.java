package com.github.rsallar.cineapi.scraping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.rsallar.cineapi.model.Cinema;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ElPais {
	//Logger logger = LoggerFactory.getLogger(ElPais.class);
	
	public void getInfo(){

		try {
			Document doc = Jsoup.connect("http://cartelera.elpais.com/cines/barcelona/").get();
			
			List<Cinema> cinemas = doc.select(".cinema_box h4 a").stream().
				//map(e-> e.select("h4 a").first()).
				map(e-> 
					Cinema.builder()
					.name(e.text())
					.link(e.attr("href"))
					.build()).collect(Collectors.toList());
			
			cinemas.stream().forEach(System.out::println);
			//cinemas.stream().forEach(a -> log.debug(a.toString()));
					
			
			
					
		} catch (IOException e) {

			log.error("Can't get cinemas from ElPais", e);
		} 
		
	
	}
	
	
    
}