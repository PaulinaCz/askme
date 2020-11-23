package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionController questionController;

    @MockBean
    private QuestionService questionService;


    @Test
    void shouldReturnListOfAllQuestions() throws Exception {

        List<ShowQuestionDTO> all = List.of(new ShowQuestionDTO(),
                new ShowQuestionDTO(), new ShowQuestionDTO());

        when(questionService.getAll()).thenReturn(all);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions/allQuestions"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(all.size())))
                .andExpect(status().isOk());


    }

    @Test
    void shouldReturn404WhenQuestionDoesntExist(){

        ResponseEntity<ShowQuestionDTO> responseEntity = questionController.getQuestionById(200L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    @WithUserDetails("johnsnow")
    void shouldSendQuestionWhenAuthenticatedUser() {

    }

    @Test
    void editQuestion() {
    }
}