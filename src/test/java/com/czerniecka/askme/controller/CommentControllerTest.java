package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.model.Comment;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CommentController commentController;

    @Autowired
    QuestionController questionController;

    @Autowired
    AnswerController answerController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestAuthenticatedUser authUser;

    @Test
    void shouldReturnEmptyListWhenUserMadeNoComments() {

        UserDetails user = authUser.authenticatedUser("Karl", "Karll123.");
        Long userId = userRepository.findByUsername(user.getUsername()).get().getUserId();

        ResponseEntity<List<ShowCommentDTO>> commentsByUser = commentController.getCommentsByUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, commentsByUser.getStatusCode());
    }

    @Test
    void shouldReturn403WhenUserNotAuthenticated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/comment/15"))
                .andExpect(status().isForbidden());

    }
//
//    @Test
//    void shouldReturnListOfCommentsByAnswerId() {
//
//        User answeringUser = authUser.authenticatedUser("ann", "Password123.");
//        UserDetails commentingUser = authUser.authenticatedUser("mary", "Mary123.");
//
//
//        List<Comment> comments = List.of(new Comment(), new Comment(), new Comment());
//
//
//
//        ResponseEntity<Long> questionResponse = questionController.sendQuestion(new AskQuestionDTO(), commentingUser);
//        ResponseEntity<Long> answerResponse = answerController.addAnswer(new AnswerDTO(), questionResponse.getBody(), answeringUser);
//
//        ResponseEntity<ShowAnswerDTO> answerById = answerController.getAnswerById(answerResponse.getBody());
//
//
//        ResponseEntity<List<ShowCommentDTO>> commentsByAnswerId = commentController.getCommentsByAnswerId(answer.getAnswerId());
//        int commentsListSize = commentsByAnswerId.getBody().size();
//
//        assertEquals(3, commentsListSize);
//
//    }
}