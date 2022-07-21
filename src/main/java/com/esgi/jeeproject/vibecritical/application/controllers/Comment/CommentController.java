package com.esgi.jeeproject.vibecritical.application.controllers.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
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

    @GetMapping("/comments/movie/{movieId}")
    public ResponseEntity<List<Comment>> getAllByMovieId(@PathVariable(value = "movieId")Long movieId){
        return ResponseEntity.ok().body(commentService.getAllByMovieId(movieId));
    }


    @GetMapping("/user/{userId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable(value = "userId")Long userId){
        return ResponseEntity.ok().body(commentService.getCommentsByUserId(userId));
    }

    @PostMapping("/user/{userId}/{movieId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "userId")Long userId,
                                                    @PathVariable(value = "movieId")Long movieId,
                                                    @RequestBody Comment comment ) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comments").toUriString());
        return ResponseEntity.created(uri).body(commentService.addComment(userId,movieId, comment));
    }

}
