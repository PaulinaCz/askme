package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Autowired
    private TestAuthenticatedUser authUser;

    @Autowired
    private QuestionController questionController;

    @Test
    void shouldReturnEmptyListOfQuestions(){



    }

    @Test
    void shouldReturn404WhenQuestionDoesntExist(){

        ResponseEntity<ShowQuestionDTO> responseEntity = questionController.getQuestionById(200L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    void shouldSendQuestionWhenAuthenticatedUser(){

        AskQuestionDTO question = new AskQuestionDTO();
        question.body = "Asking question?";
        UserDetails user = authUser.authenticatedUser("john", "Password123.");

        ResponseEntity<Long> response = questionController.sendQuestion(question, user);

       assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
    
    @Test
    void testEditQuestion() {
        AskQuestionDTO question = new AskQuestionDTO();

        question.body = "Question body?";

        UserDetails user = authUser.authenticatedUser("Glenn", "Password123.");
        ResponseEntity<Long> response = questionController.sendQuestion(question, user);
        Long questionId = response.getBody();
        AskQuestionDTO edit = new AskQuestionDTO();
        edit.body = "EDIT question body?";
        UserDetails wrongUser = authUser.authenticatedUser("jake", "Jake123.");

        ResponseEntity<String> wrongResponseEntity = questionController.editQuestion(questionId, edit, wrongUser);
        assertEquals(HttpStatus.BAD_REQUEST, wrongResponseEntity.getStatusCode());


    }
}