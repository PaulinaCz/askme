package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Rating;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    public AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/answer/{answerId}")
    public ResponseEntity<ShowAnswerDTO> getAnswerById(@PathVariable Long answerId){

        Optional<ShowAnswerDTO> answer = answerService.getById(answerId);

        return answer.map(showAnswerDTO -> new ResponseEntity<>(showAnswerDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<ShowAnswerDTO>> getAllAnswerByQuestionId(@PathVariable Long questionId){

        List<ShowAnswerDTO> answers = answerService.getAllByQuestionId(questionId);

        if(answers.isEmpty()){
            return new ResponseEntity("No answers for id " + questionId + " found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(answers, HttpStatus.OK);

    }

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<Long> addAnswer(@Valid @RequestBody AnswerDTO answerDTO,
                                          @PathVariable Long questionId,
                                          User user){

        Long answerId = answerService.addAnswer(answerDTO, questionId, user);

        return new ResponseEntity<>(answerId, HttpStatus.CREATED);

    }

    @PutMapping("{questionId}/answer/{answerId}/rate")
    public ResponseEntity<Void> rate(@PathVariable Long questionId,
                                     @PathVariable Long answerId,
                                     @RequestBody Rating rating){

        boolean changed = answerService.changeRating(answerId, rating);

        if(changed){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Answer of id" + answerId + "not found", HttpStatus.BAD_REQUEST);
        }
    }
}
