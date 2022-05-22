package com.esgi.jeeproject.vibecritical.controllers;

import com.esgi.jeeproject.vibecritical.model.Movie;
import com.esgi.jeeproject.vibecritical.repositories.MovieRepository;
import com.esgi.jeeproject.vibecritical.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title){
        try{
            List<Movie> movies = new ArrayList<Movie>();
            if(title == null){
                movieRepository.findAll().forEach(movies::add);
            }
            else {
                movieRepository.findByTitleContaining(title).forEach(movies::add);
            }
            if(movies.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        try {
            Movie _movie = movieRepository
                    .save(new Movie(movie.getTitle(), movie.getDescription()));
            return new ResponseEntity<>(_movie, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
