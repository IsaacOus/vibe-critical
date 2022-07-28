package com.esgi.jeeproject.vibecritical.infrastructure.repositories.User;

import com.esgi.jeeproject.vibecritical.domain.entities.User.Role;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWarningRepository extends JpaRepository<UserWarning, Long> {
    List<UserWarning> findByUser(User user);
}
