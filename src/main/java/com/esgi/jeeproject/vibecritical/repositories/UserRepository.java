package com.esgi.jeeproject.vibecritical.repositories;

import com.esgi.jeeproject.vibecritical.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
