package com.esgi.jeeproject.vibecritical.domain.service.Ban;

import com.esgi.jeeproject.vibecritical.domain.entities.Ban.Ban;
import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Ban.BanRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BanService {
    private final BanRepository banRepository;
    private final UserRepository userRepository;

    public Ban banUser(Long userId, Ban ban) {
        User user = userRepository.getById(userId);
        ban.setUser(user);
        return banRepository.save(ban);
    }

    public List<Ban> getAll() {
        return banRepository.findAll();
    }

    public Ban getBanByUserId(Long userId) {
        User user = userRepository.getById(userId);
        return banRepository.findByUser(user);
    }

    public boolean getUserIsBan(Long userId) {
        User user = userRepository.getById(userId);
        return banRepository.findByUser(user) != null;
    }
}
