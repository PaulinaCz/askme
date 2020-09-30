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
import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<ShowQuestionDTO> getQuestionById(@PathVariable UUID questionId){

        Optional<ShowQuestionDTO> question = questionService.getById(questionId);

        return question.map(showQuestionDTO -> new ResponseEntity<>(showQuestionDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<ShowQuestionDTO>> getAllQuestions(){

        List<ShowQuestionDTO> questions = questionService.getAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<AskQuestionDTO> sendQuestion(@RequestBody AskQuestionDTO questionDTO){
        questionService.sendQuestion(questionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editQuestion/{questionId}")
    public ResponseEntity<AskQuestionDTO> editQuestion(@PathVariable UUID questionId, @RequestBody AskQuestionDTO questionDTO)
    {
        questionService.editQuestion(questionId, questionDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
