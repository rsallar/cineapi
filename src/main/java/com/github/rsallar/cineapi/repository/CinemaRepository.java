package com.github.rsallar.cineapi.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.rsallar.cineapi.model.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {

    public Cinema findById(String id);
    public List<Cinema> findByName(String name);

}