package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.Ban.BanService;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Comment.CommentRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie.MovieRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CommentGuardService commentGuardService;
    private final BanService banService;


    public List<Comment> getCommentsByUserId(Long userId) {
        User user = userRepository.getById(userId);
        return commentRepository.findByUser(user);
    }

    public Comment addComment(Long userId, Long movieId, Comment comment) throws Exception {
        if(!banService.getUserIsBan(userId)){
            User user = userRepository.getById(userId);
            Movie movie = movieRepository.getById(movieId);
            comment.setUser(user);
            comment.setMovie(movie);
            return commentRepository.save(commentGuardService.analyseComment(comment,userId));
        }else{
            throw new Exception("User is ban, the comment is not save!");
        }
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllByMovieId(Long movieId) {
        return commentRepository.findAllByMovieId(movieId);
    }

}
