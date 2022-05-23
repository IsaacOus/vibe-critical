package com.esgi.jeeproject.vibecritical.service;

import com.esgi.jeeproject.vibecritical.domain.Role;
import com.esgi.jeeproject.vibecritical.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String usernam);
    List<User> getUsers();

}
