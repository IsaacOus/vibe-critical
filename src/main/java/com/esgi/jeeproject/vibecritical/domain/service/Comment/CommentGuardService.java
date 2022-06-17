package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserWarningService;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.WarningWord.WarningWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentGuardService {

    private final WarningWordRepository warningWordRepository;
    private final UserWarningService userWarningService;

    Comment analyseComment(Comment comment, Long userId) {
        List<WarningWord> warningWordsList = warningWordRepository.findAll();
        int warningLevel = 1;
        boolean flagAddWarning = false;
        for (WarningWord word : warningWordsList) {
            String warningWord = word.getWord();
            if (comment.getText().contains(warningWord)) {
                comment.setText(comment.getText().replaceAll(warningWord, "*".repeat(warningWord.length())));
                if(warningLevel < word.getWarningLevel()){
                    warningLevel = word.getWarningLevel();
                }
                flagAddWarning = true;
            }
        }

        if(flagAddWarning) addUserWarning(userId, warningLevel); // TODO retester ça

        return comment;
    }

    private void addUserWarning(Long userId, int warningLevel) {
            UserWarning userWarning = new UserWarning();
            userWarning.setLevelWarning(warningLevel);
            userWarningService.addUserWarning(userId, userWarning);
    }

}
