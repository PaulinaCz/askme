package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.model.Question;

import java.util.Optional;

public class QuestionToShowQuestionDTO {

    public Optional<ShowQuestionDTO> getQuestionDto(Optional<Question> questionOptional){

        if(questionOptional.isEmpty()){
            return Optional.empty();
        }else{

            Question question = questionOptional.get();

            ShowQuestionDTO questionDTO = new ShowQuestionDTO();

            questionDTO.questionId = question.getQuestionId();
            questionDTO.fromUser = question.getFromUser();
            questionDTO.body = question.getBody();
            questionDTO.timeQuestionAsked = question.getTimeQuestionAsked();

            return Optional.of(questionDTO);

        }

    }

}
