package com.esgi.jeeproject.vibecritical.application.controllers.Movie;

import com.esgi.jeeproject.vibecritical.domain.DTOs.MovieDTO;
import com.esgi.jeeproject.vibecritical.domain.service.Movie.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/movies")
public class MovieController {


    @Value("${omdbApiKey}")
    private String apiKey;

    @Autowired
    private ModelMapper modelMapper;

    private MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/{movieName}")
    private ResponseEntity<MovieDTO> getMovieInfo(@PathVariable("movieName") String movieName){

        var movieDTO = restTemplate.getForEntity("http://www.omdbapi.com/?t=" + movieName +  "&apikey="+ apiKey,MovieDTO.class);

        return ResponseEntity.ok().body(movieDTO.getBody());
    }
}
