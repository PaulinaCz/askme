package com.czerniecka.askme.controller;


import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import com.czerniecka.askme.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;
    private UserRepository userRepository;

    @Autowired
    public QuestionController(QuestionService questionService, UserRepository userRepository) {
        this.questionService = questionService;
        this.userRepository = userRepository;
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<ShowQuestionDTO> getQuestionById(@PathVariable Long questionId){

        Optional<ShowQuestionDTO> question = questionService.getById(questionId);

        return question.map(showQuestionDTO -> new ResponseEntity<>(showQuestionDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/forUser/{userId}")
    public ResponseEntity<List<ShowQuestionDTO>> getQuestionsByUser(@PathVariable Long userId){

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            return new ResponseEntity("User " + userId + " not found", HttpStatus.BAD_REQUEST);
        }else{
            List<ShowQuestionDTO> questionsByUser = questionService.getAllByUser(user.get());

            if(questionsByUser.isEmpty()){
                return new ResponseEntity("No question found for user  " + userId , HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(questionsByUser, HttpStatus.OK);
        }

    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<ShowQuestionDTO>> getAllQuestions(){

        List<ShowQuestionDTO> questions = questionService.getAll();
        if(questions.isEmpty()){
            return new ResponseEntity("There are no questions yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    @PostMapping("/askQuestion")
    public ResponseEntity<Long> sendQuestion(@Valid @RequestBody AskQuestionDTO questionDTO,
                                             @AuthenticationPrincipal UserDetails userDetails){

        Long questionId = questionService.sendQuestion(questionDTO, userDetails);

        return new ResponseEntity<>(questionId, HttpStatus.CREATED);
    }

    @PutMapping("/editQuestion/{questionId}")
    public ResponseEntity<String> editQuestion(@PathVariable Long questionId,
                                                       @Valid @RequestBody AskQuestionDTO questionDTO,
                                                        @AuthenticationPrincipal UserDetails userDetails)
    {

        if(questionService.editQuestion(questionId, questionDTO, userDetails)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Can only edit your question", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId,
                                            @AuthenticationPrincipal UserDetails userDetails){

        if(questionService.deleteQuestion(questionId, userDetails)){
            return ResponseEntity.ok("Question " + questionId + " deleted");
        }else{
            return new ResponseEntity<>("Can only delete your question", HttpStatus.BAD_REQUEST);
        }
    }
}
