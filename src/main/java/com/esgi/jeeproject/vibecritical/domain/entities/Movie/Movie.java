package com.esgi.jeeproject.vibecritical.domain.entities.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Movie implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "movie_time")
    private String runtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

