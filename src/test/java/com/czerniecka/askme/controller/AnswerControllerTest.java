package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.*;
import com.czerniecka.askme.model.Rating;
import com.czerniecka.askme.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AnswerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AnswerService answerService;

    @Autowired
    TestAuthenticatedUser authUser;

    @Autowired
    AnswerController answerController;

    @Autowired
    AuthController authController;

    @Autowired
    QuestionController questionController;

    @Test
    void shouldReturn403WhenUserNotAuthenticated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/answers/answer/1"))
                .andExpect(status().isForbidden());

    }

    @Test
    void shouldReturn404WhenNotFound() {

        authUser.authenticatedUser("john", "Password123.");

        ResponseEntity<ShowAnswerDTO> response = answerController.getAnswerById(30L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test
    void shouldUpdateRating() {
        AskQuestionDTO questionDTO = new AskQuestionDTO();
        questionDTO.body = "Is is?";

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.body = "Yup.";
        UserDetails user = authUser.authenticatedUser("john", "Password123.");

        RatingDTO rating = new RatingDTO();
        rating.rate = Rating.USEFUL.getRate();

        ResponseEntity<Long> questionResponse = questionController.sendQuestion(questionDTO, user);

        assertEquals(HttpStatus.CREATED, questionResponse.getStatusCode());

        Long questionId = questionResponse.getBody();
        ResponseEntity<Long> answerResponse = answerController.addAnswer(answerDTO, questionId, user);

        assertEquals(HttpStatus.CREATED, answerResponse.getStatusCode());

        Long answerId = answerResponse.getBody();
        answerController.rate(answerId, rating);
        ResponseEntity<ShowAnswerDTO> showAnswerResponse = answerController.getAnswerById(answerId);
        ShowAnswerDTO aDTO = showAnswerResponse.getBody();

        assertEquals(1L, aDTO.rating);

    }

    @Test
    void shouldReturnNotFoundWhenDeleteExistingAnswer(){
        AskQuestionDTO questionDTO = new AskQuestionDTO();
        questionDTO.body = "Is it right?";

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.body = "No.";
        UserDetails user = authUser.authenticatedUser("john", "Password123.");

        ResponseEntity<Long> questionResponse = questionController.sendQuestion(questionDTO, user);
        Long questionId = questionResponse.getBody();
        ResponseEntity<Long> answerResponse = answerController.addAnswer(answerDTO, questionId, user);

        assertEquals(HttpStatus.CREATED, answerResponse.getStatusCode());

        Long answerId = answerResponse.getBody();

        ResponseEntity<String> deleteResponse = answerController.deleteAnswer(answerId, user);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

        ResponseEntity<ShowAnswerDTO> findResponse = answerController.getAnswerById(answerId);
        assertEquals(HttpStatus.NOT_FOUND, findResponse.getStatusCode());

    }
}