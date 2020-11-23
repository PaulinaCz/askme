package com.czerniecka.askme.controller;

import com.czerniecka.askme.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AnswerService answerService;

    @Test
    void shouldReturn403WhenUserNotAuthenticated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/answers/answer/1"))
                .andExpect(status().isForbidden());

    }

    @Test
    void getAnswersByUser() {
    }

    @Test
    void rate() {
    }
}