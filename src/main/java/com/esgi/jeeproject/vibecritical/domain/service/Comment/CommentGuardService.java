package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Ban.Ban;
import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import com.esgi.jeeproject.vibecritical.domain.service.Ban.BanService;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserService;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserWarningService;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.WarningWord.WarningWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentGuardService {

    private final WarningWordRepository warningWordRepository;
    private final UserWarningService userWarningService;

    private final UserService userService;
    private final BanService banService;

    Comment analyseComment(Comment comment, Long userId) {
        List<WarningWord> warningWordsList = warningWordRepository.findAll();
        int warningLevel = 1;
        boolean flagAddWarning = false;
        for (WarningWord word : warningWordsList) {
            String warningWord = word.getWord();
            if (comment.getText().contains(warningWord)) {
                comment.setText(comment.getText().replaceAll(warningWord, "*".repeat(warningWord.length())));
                if (warningLevel < word.getWarningLevel()) {
                    warningLevel = word.getWarningLevel();
                }
                flagAddWarning = true;
            }
        }

        if (flagAddWarning) {
            addUserWarning(userId, warningLevel);
            banUserIfNecessary(userId);
        }

        return comment;
    }

    private void addUserWarning(Long userId, int warningLevel) {
        UserWarning userWarning = new UserWarning();
        userWarning.setLevelWarning(warningLevel);
        userWarningService.addUserWarning(userId, userWarning);
    }

    private void banUserIfNecessary(Long userId) {
        boolean userAlreadyBan = banService.getUserIsBan(userId);
        if(!userAlreadyBan){
            List<UserWarning> userWarnings = userWarningService.getWarningsByUserId(userId);
            int totalWarnings = 0;
            for (UserWarning userWarning : userWarnings) {
                totalWarnings += userWarning.getLevelWarning();
            }
            if (totalWarnings >= 10) {
                Optional<User> user = userService.getUserById(userId);
                if (user.isPresent()) {
                    banService.banUser(userId, new Ban(user.get(), new Date()));
                }
            }
        }
    }

}
