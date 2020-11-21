package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.model.Question;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionToShowQuestionDTO {

    public Optional<ShowQuestionDTO> getQuestionDto(Optional<Question> questionOptional){

        if(questionOptional.isEmpty()){
            return Optional.empty();
        }else{

            Question question = questionOptional.get();

            ShowQuestionDTO questionDTO = new ShowQuestionDTO();

            questionDTO.questionId = question.getQuestionId();
            questionDTO.username = question.getUser().getUsername();
            questionDTO.body = question.getBody();
            questionDTO.timeQuestionAsked = question.getTimeQuestionAsked();

            return Optional.of(questionDTO);

        }

    }

}
