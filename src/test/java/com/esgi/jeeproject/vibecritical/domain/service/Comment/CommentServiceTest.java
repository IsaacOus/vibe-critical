package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import com.esgi.jeeproject.vibecritical.domain.entities.Comment.Comment;
import com.esgi.jeeproject.vibecritical.domain.entities.Movie.Movie;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Comment.CommentRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.Movie.MovieRepository;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
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
import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    MovieRepository movieRepository;

    @Mock
    CommentGuardService commentGuardService;

    private static User user;

    private static Movie movie;

    private static Comment comment1;
    private static Comment comment2;

    @BeforeAll
    static void setUp(){
        user = new User(1L,"Bob","bob","1234",null);
        movie = new Movie( 1L,"Scarface", "09 Dec 1983", "Crime", "Brian De Palma","170");
        comment1 = new Comment(movie,user,"Lorem Ipsum");
        comment2 = new Comment(movie,user,"Dolor Sit Amet");
    }

    @DisplayName("Find all comments")
    @Test
    void test_find_all_comments(){
        doReturn(Arrays.asList(comment1,comment2)).when(repository).findAll();

        Optional<List<Comment>> returnedComments = Optional.ofNullable(commentService.getAll());

        assertAll(
                () -> assertFalse(returnedComments.isEmpty()),
                () -> assertEquals(2, returnedComments.get().size(), "getAllComment should return 2 comments")
        );
    }




    @DisplayName("Find comment by user id")
    @Test
    void test_find_comment_by_user_id(){

        doReturn(Arrays.asList(this.comment1)).when(repository).findByUser(user);
        doReturn(user).when(userRepository).getById(anyLong());

        Optional<List<Comment>> returnedComments = Optional.ofNullable(commentService.getCommentsByUserId(user.getId()));

        assertAll(
                () -> assertFalse(returnedComments.isEmpty()),
                () -> assertEquals(1, returnedComments.get().size())
                //TODO assertion on content
        );


    }



}