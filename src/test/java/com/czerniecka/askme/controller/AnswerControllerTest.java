package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.LoginUserDTO;
import com.czerniecka.askme.dto.RatingDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Rating;
import com.czerniecka.askme.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
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

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.body = "Yup.";
        UserDetails user = authUser.authenticatedUser("john", "Password123.");
        RatingDTO rating = new RatingDTO();
        rating.rate = Rating.USEFUL.getRate();

        ResponseEntity<Long> longResponse = answerController.addAnswer(answerDTO, 1L, user);
        Long index = longResponse.getBody();
        answerController.rate(index, rating);
        ResponseEntity<ShowAnswerDTO> response = answerController.getAnswerById(index);
        ShowAnswerDTO aDTO = response.getBody();

        assertEquals(1, aDTO.rating);


    }
}