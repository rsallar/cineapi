package com.github.rsallar.cineapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class CineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineApiApplication.class, args);
	}
}