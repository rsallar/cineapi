package com.github.rsallar.cineapi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Cinema {
	@Id @JsonIgnore
    private String id;
	private String name;

	private List<Movie> movies = new ArrayList<>();
	
}
