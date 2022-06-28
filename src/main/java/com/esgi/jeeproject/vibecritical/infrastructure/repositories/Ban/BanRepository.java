package com.esgi.jeeproject.vibecritical.infrastructure.repositories.Ban;

import com.esgi.jeeproject.vibecritical.domain.entities.Ban.Ban;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanRepository extends JpaRepository<Ban, Long> {
    Ban findByUser(User user);
}
