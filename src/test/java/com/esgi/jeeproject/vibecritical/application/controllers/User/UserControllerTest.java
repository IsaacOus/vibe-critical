package com.esgi.jeeproject.vibecritical.application.controllers.User;

import com.esgi.jeeproject.vibecritical.VibeCriticalApplication;
import com.esgi.jeeproject.vibecritical.domain.entities.User.User;
import com.esgi.jeeproject.vibecritical.domain.service.User.UserService;
import com.esgi.jeeproject.vibecritical.infrastructure.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = UserController.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /users succes")
    void testGetUsersSuccess() throws Exception{
        User user1 = new User(1L,"Chris","chris","1234",null);
        User user2 = new User(2L,"Smith","smith","1234",null);
        doReturn(Lists.newArrayList(user1, user2)).when(service).getUsers();

        mockMvc.perform(get("/api/users/"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].name", is("Chris")))
                    .andExpect(jsonPath("$[0].username", is("chris")))
                    .andExpect(jsonPath("$[1].name", is("Smith")))
                    .andExpect(jsonPath("$[1].username", is("smith")));
    }

    @Test
    @DisplayName("POST /api/users/save")
    void saveUser() throws Exception{
        User user = new User(1L,"Joe","joe","1234",null);
        doReturn(user).when(service).saveUser(user);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/users/save")
                        .content(asJsonString(new User(1L,"Joe","joe","1234",null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
