package com.esgi.jeeproject.vibecritical.domain.service.Comment;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentController {
    private MockMvc mockMvc;

    @Test
    public void get_comment() throws Exception {
        mockMvc.perform(get("/api/comments"))
                .andExpect(status().is4xxClientError());
    }
}
