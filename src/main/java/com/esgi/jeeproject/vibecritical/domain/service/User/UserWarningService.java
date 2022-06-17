package com.esgi.jeeproject.vibecritical.domain.service.User;

import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserWarningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWarningService {

    private final UserWarningRepository userWarningRepository;
    private final UserRepository userRepository;

    public List<UserWarning> getWarningsByUserId(Long userId) {
        return userWarningRepository.findByUser(userRepository.getById(userId));
    }

    public UserWarning addUserWarning(Long userId, UserWarning userWarning) {
        User user = userRepository.getById(userId);
        userWarning.setUser(user);
        System.out.println(user);
        return userWarningRepository.save(userWarning);//TODO try catch with custom exception
    }

    public List<UserWarning> findAll() {
        return this.userWarningRepository.findAll();
    }
}
