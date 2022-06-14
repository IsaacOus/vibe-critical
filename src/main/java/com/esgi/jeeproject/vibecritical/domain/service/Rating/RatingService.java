package com.esgi.jeeproject.vibecritical.domain.service.Rating;

import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie.MovieRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Rating.RatingRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public Rating saveRating(Rating rating, Long movieId, Long userId) {
       User user = userRepository.getById(userId);
       rating.setUser(user);
        Movie movie = movieRepository.getById(movieId);
        rating.setMovie(movie);
       return ratingRepository.save(rating);//TODO try catch with custom exception
    }

    public Rating getRatingById(Long ratingId) {
        return ratingRepository.getById(ratingId);
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

}
