package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerToShowAnswerDTO {

    Optional<ShowAnswerDTO> getAnswerDto(Optional<Answer> answerOptional){

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            Answer answer = answerOptional.get();
            ShowAnswerDTO answerDTO = new ShowAnswerDTO();

            answerDTO.answerId = answer.getAnswerId();
            answerDTO.answeringUserId = answer.getAnsweringUserId();
            answerDTO.questionId = answer.getQuestionId();
            answerDTO.body = answer.getBody();
            answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
            answerDTO.rating = answer.getRating();

            return Optional.of(answerDTO);
        }

    }

}
