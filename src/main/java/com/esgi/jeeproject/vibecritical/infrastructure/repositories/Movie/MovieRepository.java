package com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie;

import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
}
