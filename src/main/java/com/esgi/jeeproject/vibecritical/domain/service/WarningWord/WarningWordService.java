package com.esgi.jeeproject.vibecritical.domain.service.WarningWord;

import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.WarningWord.WarningWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WarningWordService {

    private final WarningWordRepository warningWordsRepository;

    public int getWarningLevelByWarningWord(String word) {
        return warningWordsRepository.getByName(word).getWarningLevel();
    }

    public WarningWord addWarningWord(WarningWord warningWords) {
        return warningWordsRepository.save(warningWords);
    }

    public List<WarningWord> getAll() {
        return warningWordsRepository.findAll();
    }
}
