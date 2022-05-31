package com.esgi.jeeproject.vibecritical.service.User;

import com.esgi.jeeproject.vibecritical.domain.User.Role;
import com.esgi.jeeproject.vibecritical.domain.User.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
