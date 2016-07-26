package com.github.rsallar.cineapi.sraping;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ElPais {
	Logger logger = LoggerFactory.getLogger(ElPais.class);
	public void test(){

		try {
			Document doc = Jsoup.connect("http://cartelera.elpais.com/cines/barcelona/").get();
			
			/*doc.select(".cinema_box").stream().
				map(e-> logger.info(e.select("h4 a").first().text()));
				//logger.info(e.select("h4 a").first().text());
				//logger.info(e.select("h4 a").first().text());*/
					
			
		} catch (IOException e) {

			logger.error("Can't get cinemas from ElPais", e);
		} 
		
	
	}
    
}