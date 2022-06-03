package com.esgi.jeeproject.vibecritical.application.controllers.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import com.esgi.jeeproject.vibecritical.domain.service.Comment.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAll(){
        return ResponseEntity.ok().body(commentService.getAll());
    }

    @GetMapping("/user/{userId}/comments")
    public ResponseEntity<List<Comment>> getUserWarningsById(@PathVariable(value = "userId")Long userId){
        return ResponseEntity.ok().body(commentService.getCommentsByUserId(userId));
    }

    @PostMapping("/user/{userId}/{movieId}/comments")
    public ResponseEntity<Comment> createRating(@PathVariable(value = "userId")Long userId,
                                                    @PathVariable(value = "movieId")Long movieId,
                                                    @RequestBody Comment comment ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comments").toUriString());
        return ResponseEntity.created(uri).body(commentService.addComment(userId,movieId, comment));
    }

}
