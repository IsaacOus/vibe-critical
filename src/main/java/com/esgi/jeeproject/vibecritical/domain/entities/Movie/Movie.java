package com.esgi.jeeproject.vibecritical.domain.entities.Movie;

import com.esgi.jeeproject.vibecritical.domain.DTOs.MovieDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "movie_time")
    private String runtime;

    public Movie(String name, String releaseDate, String genre, String director, String runtime) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
    }

    public Movie() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public static Movie fromMovieDTO(MovieDTO movieDTO){
        return new Movie(movieDTO.getName(),movieDTO.getReleaseDate(),movieDTO.getGenre(),movieDTO.getDirector(),movieDTO.getRuntime());
    }

}

