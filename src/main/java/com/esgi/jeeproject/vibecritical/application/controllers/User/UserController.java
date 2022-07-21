package com.esgi.jeeproject.vibecritical.application.controllers.User;

import com.esgi.jeeproject.vibecritical.domain.entities.User.Role;
import com.esgi.jeeproject.vibecritical.domain.entities.User.RoleToUserForm;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/context")
    public Object getCurrentUserContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUsersById(@PathVariable("userId") Long userId){
        return ResponseEntity.of(userService.getUserById(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
}
