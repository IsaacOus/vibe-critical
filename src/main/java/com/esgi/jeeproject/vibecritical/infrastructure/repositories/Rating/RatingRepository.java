package com.esgi.jeeproject.vibecritical.infrastructure.repositories.Rating;

import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
