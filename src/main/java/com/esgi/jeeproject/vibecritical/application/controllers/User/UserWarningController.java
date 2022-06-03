package com.esgi.jeeproject.vibecritical.application.controllers.User;

import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserWarningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserWarningController {

    private final UserWarningService userWarningService;

    public UserWarningController(UserWarningService userWarningService) {
        this.userWarningService = userWarningService;
    }


    @GetMapping("/user/warnings/{userId}")
    public ResponseEntity<List<UserWarning>> getUserWarningsById(@PathVariable(value = "userId")Long userId){
        return ResponseEntity.ok().body(userWarningService.getWarningsByUserId(userId));
    }

    @PostMapping("/user/warnings/{userId}")
    public ResponseEntity<UserWarning> createRating(@PathVariable(value = "userId")Long userId,
                                               @RequestBody UserWarning userWarning ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/ratings").toUriString());
        return ResponseEntity.created(uri).body(userWarningService.addUserWarning(userId, userWarning));
    }

}
