package com.github.rsallar.cineapi.scraping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.github.rsallar.cineapi.model.Cinema;
import com.github.rsallar.cineapi.model.Movie;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Avui {
	private static int TIMEOUT = 20000;
	private static String LINK = "http://www.elpuntavui.cat";
	
	
	/**
	 * Gets a list of cinemas with movie and sessions from news site "www.elpuntavui.cat".
	 * @return
	 */
	@CacheEvict(value = "cinema", beforeInvocation = false)
	public List<Cinema> getInfo(){
		List<Cinema> cinemas = Lists.newArrayList();
		try {
			cinemas = getPopulatedCinemas();
		} catch (Throwable e) {
			log.error("Can't get cinemas from www.elpuntavui.cat", e);
		}
		
		return cinemas;
			
	}
	
	private List<Cinema> getPopulatedCinemas() throws IOException {
		List<Cinema> cinemas;
		Document doc = Jsoup.connect("http://www.elpuntavui.cat/cinema/locals/barcelona.html").timeout(TIMEOUT).get();
		
		List<String> links = Lists.newArrayList();
		cinemas = doc.select(".sala-peli a").stream().
			map(e-> {
					Cinema c= new Cinema();
					c.setName(e.text());
					links.add((LINK + e.attr("href")));
					return c;
				}
				).collect(Collectors.toList());

		fillWithMovies(cinemas, links);
		return cinemas;
	}
	
	private List<Cinema> fillWithMovies(List<Cinema> cinemas, List<String> links)  throws IOException{
		
		for (int i = 0; i<cinemas.size(); i++){

			log.info("scrapping cinema {} of {}. name: {}", i+1, cinemas.size(), cinemas.get(i).getName());
			
			Document doc = Jsoup.connect(links.get(i)).timeout(TIMEOUT).get();
			
			List<String> dateLinks =  doc.select(".dies-projeccions li.dies a").stream().map(e-> e.attr("href")).filter(o-> o!=null && !o.equals("#")).collect(Collectors.toList());
			
			Map<String, Movie> moviesMap = new HashMap<>(); //all movies for a cinema
			
			for(int j=0; j<dateLinks.size(); j++){
				String dateLink = dateLinks.get(j);
				String dateTxt= dateLink.substring(dateLink.indexOf("dia=")+4);
				if(j>0){ //we already have first sessions info.
					doc = Jsoup.connect(LINK +dateLink).timeout(TIMEOUT).get();
				}

				log.info("scrapping day: {}", dateTxt);
				
				for (Element e: doc.select("div.fila.row")){
					String name = e.select("a").first().text();
					
					if(!moviesMap.containsKey(name)){
						Movie movie = new Movie();
						movie.setName(name);
						moviesMap.put(name, movie);
					}
					String sessions = e.select("div.horari-peli").first().text();
					moviesMap.get(name).getSessions().addAll(parseSessions(sessions, dateTxt));
				}		
				
			}
			cinemas.get(i).getMovies().addAll(moviesMap.values());	
		}

		return cinemas;
	}
	
	/**
	 * 
	 * @param sessionsStr strig like "15.40 - 17.50 - 20.00"
	 * @param dateTxt date like dd/MM/yyyy
	 * @return
	 */
	private List<Date> parseSessions(String sessionsStr, String dateTxt){
		
		sessionsStr = sessionsStr.replaceAll(" ", "");
		String[] splittedSessions = sessionsStr.split("-");
		List<Date> sessions = new ArrayList<>();
		for(String splittedSession: splittedSessions){
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH.mm");
			try {
				sessions.add(df.parse(dateTxt + " " +splittedSession));
			} catch (ParseException e) {
				log.error("cant parse date {}", splittedSession, e);
			}
		}
		return sessions;
	}


}
