package com.esgi.jeeproject.vibecritical.service.Movie;

import com.esgi.jeeproject.vibecritical.domain.Movie.Movie;
import com.esgi.jeeproject.vibecritical.repositories.Movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(String name) {
        return movieRepository.findByName(name);
    }
}
