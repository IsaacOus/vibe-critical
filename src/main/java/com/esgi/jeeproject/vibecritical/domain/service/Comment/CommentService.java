package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByUserId(Long userId);

    Comment addComment(Long userId,Long movieId,Comment comment);

    List<Comment> getAll();
}
