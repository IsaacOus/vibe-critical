package com.esgi.jeeproject.vibecritical.domain.service.Movie;

import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {


    private final MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovie(String name) {
        return movieRepository.findByName(name);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
