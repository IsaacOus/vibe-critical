package com.esgi.jeeproject.vibecritical.infrastructure.repositories.WarningWord;

import com.esgi.jeeproject.vibecritical.domain.entities.Rating.Rating;
import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarningWordRepository extends JpaRepository<WarningWord, Long> {

    @Query("SELECT r FROM WarningWord r WHERE r.word = :word")
    WarningWord getByName(String word);
}
