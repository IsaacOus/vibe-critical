package com.esgi.jeeproject.vibecritical.domain.service.Movie;

import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Movie getMovie(String name);
    List<Movie> getAllMovies();
}
