package com.esgi.jeeproject.vibecritical.application.controllers.Ban;

import com.esgi.jeeproject.vibecritical.domain.entities.Ban.Ban;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.service.Ban.BanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BanController {
    private final BanService banService;

    public BanController(BanService banService) {
        this.banService = banService;
    }

    @GetMapping("/ban")
    public ResponseEntity<List<Ban>> getAllBan(){
        return ResponseEntity.ok().body(banService.getAll());
    }

    @GetMapping("/ban/{userId}")
    public ResponseEntity<Ban> getBanByUserId(@PathVariable(value = "userId")Long userId){
        return ResponseEntity.ok().body(banService.getBanByUserId(userId));
    }
}
