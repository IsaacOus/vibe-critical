package com.esgi.jeeproject.vibecritical.service.Movie;

import com.esgi.jeeproject.vibecritical.domain.Movie.Movie;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Movie getMovie(String name);
}
