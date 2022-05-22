package com.esgi.jeeproject.vibecritical.services;

import com.esgi.jeeproject.vibecritical.model.Movie;

public interface MovieService {

    Iterable<Movie> listAllMovies();

    Movie getMovieById(Integer id);

}
