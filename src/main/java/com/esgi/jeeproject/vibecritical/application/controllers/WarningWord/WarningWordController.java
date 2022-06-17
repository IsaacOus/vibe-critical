package com.esgi.jeeproject.vibecritical.application.controllers.WarningWord;

import com.esgi.jeeproject.vibecritical.domain.entities.WarningWord.WarningWord;
import com.esgi.jeeproject.vibecritical.domain.service.WarningWord.WarningWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WarningWordController {

    private final WarningWordService warningWordsService;

    public WarningWordController(WarningWordService warningWordsService) {
        this.warningWordsService = warningWordsService;
    }


    @GetMapping("/warningWords")
    public ResponseEntity<List<WarningWord>> getAll(){
        return ResponseEntity.ok().body(warningWordsService.getAll());
    }

    @GetMapping("/warningWords/warningLevel/{warningWord}")
    public ResponseEntity<Integer> getWarningLevelByWord(@PathVariable(value = "warningWord")String warningWord){
        return ResponseEntity.ok().body(warningWordsService.getWarningLevelByWarningWord(warningWord));
    }

    @PostMapping("/warningWords")
    public ResponseEntity<WarningWord> createRating(@RequestBody WarningWord warningWords ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/warningWords").toUriString());
        return ResponseEntity.created(uri).body(warningWordsService.addWarningWord(warningWords));
    }

}
