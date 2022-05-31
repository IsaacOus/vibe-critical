package com.esgi.jeeproject.vibecritical.repositories.User;

import com.esgi.jeeproject.vibecritical.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
