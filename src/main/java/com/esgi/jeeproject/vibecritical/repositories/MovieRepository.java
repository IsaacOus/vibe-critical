package com.esgi.jeeproject.vibecritical.repositories;

import com.esgi.jeeproject.vibecritical.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAll();

    List<Movie> findByTitleContaining(String title);

}
