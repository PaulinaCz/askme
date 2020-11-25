package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.repository.QuestionRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Autowired
    private TestAuthenticatedUser authUser;

    @Autowired
    private QuestionController questionController;

    @Autowired
    QuestionRepository questionRepository;


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
    void shouldSendQuestionWhenAuthenticatedUser(){

        AskQuestionDTO question = new AskQuestionDTO();

        question.body = "Asking question?";

        UserDetails user = authUser.authenticatedUser("john", "Password123.");

        ResponseEntity<Long> response = questionController.sendQuestion(question, user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ResponseEntity<ShowQuestionDTO> questionById = questionController.getQuestionById(response.getBody());
        assertEquals(HttpStatus.OK, questionById.getStatusCode());
        ShowQuestionDTO qDTO = questionById.getBody();
        assertEquals(user.getUsername(), qDTO.userDto.username);


    }
    
    @Test
    void testEditQuestion() {
        AskQuestionDTO question = new AskQuestionDTO();

        question.body = "Question body?";

        UserDetails user = authUser.authenticatedUser("john", "Password123.");
        ResponseEntity<Long> response = questionController.sendQuestion(question, user);
        Long questionId = response.getBody();
        AskQuestionDTO edit = new AskQuestionDTO();
        edit.body = "EDIT question body?";
        UserDetails wrongUser = authUser.authenticatedUser("jake", "Jake123.");

        ResponseEntity<String> editResponseEntity = questionController.editQuestion(questionId, edit, user);
        ResponseEntity<String> wrongResponseEntity = questionController.editQuestion(questionId, edit, wrongUser);


        assertEquals(HttpStatus.CREATED, editResponseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, wrongResponseEntity.getStatusCode());
    }
}