package com.github.rsallar.cineapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Movie {
	
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private List<Date> sessions = new ArrayList<>();
}
