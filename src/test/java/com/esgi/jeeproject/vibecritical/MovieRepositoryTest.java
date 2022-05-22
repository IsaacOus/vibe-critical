package com.esgi.jeeproject.vibecritical;

import com.esgi.jeeproject.vibecritical.model.Movie;
import com.esgi.jeeproject.vibecritical.repositories.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("Should find no movies if repository is empty")
    public void should_find_no_movies_if_repository_is_empty(){
        Iterable<Movie> movies = movieRepository.findAll();
        assertThat(movies).isEmpty();
    }

}