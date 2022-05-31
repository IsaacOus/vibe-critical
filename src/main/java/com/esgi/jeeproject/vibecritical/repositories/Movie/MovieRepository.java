package com.esgi.jeeproject.vibecritical.repositories.Movie;

import com.esgi.jeeproject.vibecritical.domain.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
}
