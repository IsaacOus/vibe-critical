package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import com.esgi.jeeproject.vibecritical.domain.service.Ban.BanService;
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
    private final BanService banService;

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

        if(flagAddWarning){
            addUserWarning(userId, warningLevel);
            checkBanUser(userId);
        }

        return comment;
    }

    private void addUserWarning(Long userId, int warningLevel) {
        UserWarning userWarning = new UserWarning();
        userWarning.setLevelWarning(warningLevel);
        userWarningService.addUserWarning(userId, userWarning);
    }

    private void checkBanUser(Long userId){
        List<UserWarning> userWarnings = userWarningService.getWarningsByUserId(userId);
        int totalWarnings = 0;
        for(UserWarning userWarning : userWarnings){
            totalWarnings += userWarning.getLevelWarning();
        }
        if(totalWarnings >= 10){
            //Ban ban = new Ban();
            //ban.setId((Long) UUID.randomUUID());
            //banService.banUser(userId,ban);
            //TODO ban user
        }
    }

}