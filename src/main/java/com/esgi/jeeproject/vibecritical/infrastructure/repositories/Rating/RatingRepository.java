package com.esgi.jeeproject.vibecritical.infrastructure.repositories.Rating;

import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT r FROM Rating r WHERE r.movie.name = :movieName")
    List<Rating> findByMovieName(String movieName);

    @Query("SELECT r FROM Rating r WHERE r.user.id = :userId AND r.movie.id = :movieName")

    Rating findOneByUserIdAndMovieId(Long userId, Long movieName);
}
