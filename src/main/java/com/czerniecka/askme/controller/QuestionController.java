package com.czerniecka.askme.controller;


import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return new ResponseEntity<>(questionsByUser, HttpStatus.OK);

    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<ShowQuestionDTO>> getAllQuestions(){

        List<ShowQuestionDTO> questions = questionService.getAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Long> sendQuestion(@RequestBody AskQuestionDTO questionDTO){

        Long questionId = questionService.sendQuestion(questionDTO);

        return new ResponseEntity<>(questionId, HttpStatus.CREATED);
    }

    @PutMapping("/editQuestion/{questionId}")
    public ResponseEntity<AskQuestionDTO> editQuestion(@PathVariable Long questionId, @RequestBody AskQuestionDTO questionDTO)
    {
        questionService.editQuestion(questionId, questionDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
