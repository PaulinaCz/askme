package com.czerniecka.askme.controller;


import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
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

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<ShowQuestionDTO> getQuestionById(@PathVariable Long questionId){

        Optional<ShowQuestionDTO> question = questionService.getById(questionId);

        return question.map(showQuestionDTO -> new ResponseEntity<>(showQuestionDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/forUser/{userId}")
    public ResponseEntity<List<ShowQuestionDTO>> getQuestionsByUserId(@PathVariable Long userId){

        List<ShowQuestionDTO> questionsByUser = questionService.getAllByUser(userId);

        if(questionsByUser.isEmpty()){
            return new ResponseEntity("User " + userId + " not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(questionsByUser, HttpStatus.OK);

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
    public ResponseEntity<AskQuestionDTO> editQuestion(@PathVariable Long questionId, @Valid @RequestBody AskQuestionDTO questionDTO)
    {

        questionService.editQuestion(questionId, questionDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
