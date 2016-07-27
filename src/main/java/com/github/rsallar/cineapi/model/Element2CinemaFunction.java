package com.github.rsallar.cineapi.model;

import java.util.function.Function;

public class Element2CinemaFunction implements Function<String, Cinema> {

	@Override
	public Cinema apply(String name) {
		return Cinema.builder().name(name).build();
	}

}
