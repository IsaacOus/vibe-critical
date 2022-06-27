package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Comment.CommentRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository repository;

    @Mock
    UserRepository userRepository;

    @DisplayName("Find all comments")
    @Test
    void test_find_all_comments(){
        Movie movie = new Movie( "Scarface", "09 Dec 1983", "Crime", "Brian De Palma","170");
        User user = new User(1L,"Bob","bob","1234",null);

        Comment comment1 = new Comment(movie,user,"Lorem Ipsum");
        Comment comment2 = new Comment(movie,user,"Dolor Sit Amet");

        doReturn(Arrays.asList(comment1,comment2)).when(repository).findAll();

        Optional<List<Comment>> returnedComments = Optional.ofNullable(commentService.getAll());

        assertAll(
                () -> assertFalse(returnedComments.isEmpty()),
                () -> assertEquals(2, returnedComments.get().size(), "getAllComment should return 2 comments")
        );
    }


    @DisplayName("Add comment")
    @Test
    void test_add_comment_to_a_movie(){

    }

    @DisplayName("Find comment by user id")
    @Test
    void test_find_comment_by_user_id(){

        Movie movie = new Movie( "Scarface", "09 Dec 1983", "Crime", "Brian De Palma","170");
        User user = new User(1L,"Bob","bob","1234",null);
        Comment comment = new Comment(movie,user,"Lorem Ipsum");

        doReturn(Arrays.asList(comment)).when(repository).findByUser(user);
        doReturn(user).when(userRepository).getById(anyLong());

        Optional<List<Comment>> returnedComments = Optional.ofNullable(commentService.getCommentsByUserId(user.getId()));

        assertAll(
                () -> assertFalse(returnedComments.isEmpty()),
                () -> assertEquals(1, returnedComments.get().size())
                //TODO assertion on content
        );


    }



}