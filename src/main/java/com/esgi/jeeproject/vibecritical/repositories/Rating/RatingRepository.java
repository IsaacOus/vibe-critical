package com.esgi.jeeproject.vibecritical.repositories.Rating;

import com.esgi.jeeproject.vibecritical.domain.Rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
