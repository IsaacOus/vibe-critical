package com.esgi.jeeproject.vibecritical.infrastructure.repositories.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.entities.User.UserWarning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
}
