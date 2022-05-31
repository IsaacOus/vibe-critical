package com.esgi.jeeproject.vibecritical.service.Movie;

import com.esgi.jeeproject.vibecritical.domain.Movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {


    @Override
    public Movie getMovie(String name) {
        return null;
    }
}
