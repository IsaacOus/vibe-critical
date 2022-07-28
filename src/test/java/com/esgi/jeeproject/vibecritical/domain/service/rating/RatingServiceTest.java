package com.esgi.jeeproject.vibecritical.domain.service.rating;

import com.esgi.jeeproject.vibecritical.application.controllers.User.UserController;
import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.Rating.RatingService;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie.MovieRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Rating.RatingRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @InjectMocks
    RatingService ratingService;

    @Mock
    RatingRepository ratingRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    MovieRepository movieRepository;

    private static User user;
    private static Movie movie;
    private static Rating rating;

    @BeforeAll
    static void setUp(){
        user = new User(1L,"Bob","bob","1234",null);
        movie = new Movie( 1L,"Scarface", "09 Dec 1983", "Crime", "Brian De Palma","170");
        rating = new Rating(1L, movie, user, 3.5f, new Date());
    }


    @DisplayName("get rating by user id")
    @Test
    void get_rating_by_id(){

        doReturn(rating).when(ratingRepository).getById(rating.getId());

        Optional<Rating> returnedRating = Optional.ofNullable(ratingService.getRatingById(rating.getId()));

        assertAll(
                () -> assertFalse(returnedRating.equals(null)),
                () -> assertEquals(1L, returnedRating.get().getId())
                //TODO assertion on content
        );
    }

    @DisplayName("get rating")
    @Test
    void get_rating(){

        doReturn(Arrays.asList(rating)).when(ratingRepository).findAll();

        Optional<List<Rating>> returnedRating = Optional.ofNullable(ratingService.getRatings());

        assertAll(
                () -> assertFalse(returnedRating.isEmpty()),
                () -> assertEquals(1, returnedRating.get().size())
                //TODO assertion on content
        );
    }

    @DisplayName("get rating")
    @Test
    void get_ratings_by_movie_name(){

       User user2 = new User(2L,"Malick","Diop","12345",null);
       Rating rating2 = new Rating(2L, movie, user2, 5.5f, new Date());



        doReturn(Arrays.asList(rating, rating2)).when(ratingRepository).findByMovieName(movie.getName());

        Optional<List<Rating>> returnedRating = Optional.ofNullable(ratingService.getAllRatingsByMovieName(movie.getName()));

        assertAll(
                () -> assertFalse(returnedRating.isEmpty()),
                () -> assertEquals(2, returnedRating.get().size())
                //TODO assertion on content
        );
    }

    @DisplayName("get global rating")
    @Test
    void get_global_ratings_by_movie_name(){

        User user2 = new User(2L,"Malick","Diop","12345",null);
        Rating rating2 = new Rating(2L, movie, user2, 5.5f, new Date());



        doReturn(Arrays.asList(rating, rating2)).when(ratingRepository).findByMovieName(movie.getName());

        Optional<Float> globalRating = Optional.ofNullable(ratingService.getGlobalRatingByMovieName(movie.getName()));
        float rakingTest = (rating.getRating() + rating2.getRating()) / 2;

        assertAll(
                () -> assertEquals(rakingTest, globalRating.get())
                //TODO assertion on content
        );
    }
}

