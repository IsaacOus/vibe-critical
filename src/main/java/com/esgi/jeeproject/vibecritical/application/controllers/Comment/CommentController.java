package com.esgi.jeeproject.vibecritical.application.controllers.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.Comment.CommentService;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
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

    @PostMapping("/user/{movieId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "movieId")Long movieId,
                                                    @RequestBody Comment comment ) throws Exception {
        User currentUser = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comments").toUriString());
        return ResponseEntity.created(uri).body(commentService.addComment(currentUser.getId(),movieId, comment));
    }

}
