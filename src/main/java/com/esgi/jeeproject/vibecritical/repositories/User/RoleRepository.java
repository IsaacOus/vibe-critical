package com.esgi.jeeproject.vibecritical.repositories.User;

import com.esgi.jeeproject.vibecritical.domain.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
