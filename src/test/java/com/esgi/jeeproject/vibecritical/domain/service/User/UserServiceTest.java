package com.esgi.jeeproject.vibecritical.domain.service.User;

import com.esgi.jeeproject.vibecritical.application.controllers.User.UserController;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.infrastructure.repositories.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository repository;

    @Test
    @DisplayName("Test findAll")
    void test_find_all_users(){
        User user1 = new User(1L,"Bob","bob","1234",null);
        User user2 = new User(2L,"Smith","smith","1234",null);
        doReturn(Arrays.asList(user1,user2)).when(repository).findAll();

        Optional<List<User>> returnedUsers = Optional.ofNullable(userService.getUsers());

        assertAll(
                () -> assertFalse(returnedUsers.isEmpty()),
                () -> assertEquals(2, returnedUsers.get().size(),"getUsers should return 2 users")
        );
    }

    @Test
    @DisplayName("Test findByUserName Success")
    void test_find_user_by_username(){
        User user = new User(1L,"Bob","bob","1234",null);
        doReturn(user).when(repository).findByUsername("bob");

        Optional<User> userReturned = Optional.ofNullable(userService.getUser("bob"));

        assertAll(
                () -> Assertions.assertTrue(userReturned.isPresent()),
                () -> Assertions.assertSame(userReturned.get(),user)
        );
    }


    @Test
    @DisplayName("Test save User")
    void test_save_user(){
        User user = new User(1L,"Bob","bob","1234",null);
        doReturn(user).when(repository).save(user);

        User returnedUser = repository.save(user);

        assertNotNull(returnedUser,"The saved user should not be null");
    }

}