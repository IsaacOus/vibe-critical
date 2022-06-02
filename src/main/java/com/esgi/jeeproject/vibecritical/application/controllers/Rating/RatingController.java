package com.esgi.jeeproject.vibecritical.application.controllers.Rating;

import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import com.esgi.jeeproject.vibecritical.domain.service.Rating.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok().body(ratingService.getRatings());
    }

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId){
        return ResponseEntity.ok().body(ratingService.getRatingById(ratingId));
    }

    @PostMapping("/users/{userId}/ratings/{movieId}")
    public ResponseEntity<Rating> createRating(@PathVariable(value = "userId")Long userId,
                                               @PathVariable(value = "movieId")Long movieId,
                                               @RequestBody Rating rating ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/ratings").toUriString());
        return ResponseEntity.created(uri).body(ratingService.saveRating(rating, movieId, userId));
    }

}
