package com.esgi.jeeproject.vibecritical.domain.service.Rating;

import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.Ban.BanService;
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
    private final BanService banService;


    public Rating saveRating(Rating rating, Long movieId, Long userId) throws Exception {
        if(!banService.getUserIsBan(userId)){
            Rating existRating = this.ratingRepository.findOneByUserIdAndMovieId(userId, movieId);
            if (existRating != null) {
                existRating.setRating(rating.getRating());
                return ratingRepository.save(existRating);
            } else {
                User user = userRepository.getById(userId);
                rating.setUser(user);
                Movie movie = movieRepository.getById(movieId);
                rating.setMovie(movie);
                return ratingRepository.save(rating);
            }
        }else{
            throw new Exception("User is ban, the rating is not save!");
        }
    }

    public Rating getRatingById(Long ratingId) {
        return ratingRepository.getById(ratingId);
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getAllRatingsByMovieName(String movieName) {
        return ratingRepository.findByMovieName(movieName);
    }

    public float getGlobalRatingByMovieName(String movieName) {
        List<Rating> ratings = this.getAllRatingsByMovieName(movieName);
        float globalRating = 0;
        for (Rating rating : ratings) {
            globalRating += rating.getRating();
        }
        return globalRating / ratings.size();
    }

}
