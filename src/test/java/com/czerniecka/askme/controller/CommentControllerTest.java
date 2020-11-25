package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.*;
import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.model.Comment;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.AnswerRepository;
import com.czerniecka.askme.repository.QuestionRepository;
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
import java.util.Optional;

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
    AnswerRepository answerRepository;

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

    @Test
    void shouldReturnListOfCommentsByAnswerId() {

        User user1 = authUser.authenticatedUser("ann", "Password123.");
        UserDetails user2 = authUser.authenticatedUser("mary", "Mary123.");

        ResponseEntity<Long> questionResponse = questionController.sendQuestion(new AskQuestionDTO(), user2);
        assertEquals(HttpStatus.CREATED, questionResponse.getStatusCode());

        ResponseEntity<Long> answerResponse = answerController.addAnswer(new AnswerDTO(), questionResponse.getBody(), user1);
        assertEquals(HttpStatus.CREATED, answerResponse.getStatusCode());

        Long answerId = answerResponse.getBody();
        commentController.addComment(new WriteCommentDTO(), answerId, user2);
        commentController.addComment(new WriteCommentDTO(), answerId, user2);
        commentController.addComment(new WriteCommentDTO(), answerId, user2);

        ResponseEntity<List<ShowCommentDTO>> commentsByAnswerId = commentController.getCommentsByAnswerId(answerId);
        int size = commentsByAnswerId.getBody().size();

        assertEquals(3, size);

    }
}