package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerToShowAnswerDTO {

    public Optional<ShowAnswerDTO> getOptionalAnswerDto(Optional<Answer> answerOptional){

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            Answer answer = answerOptional.get();
            ShowAnswerDTO answerDTO = new ShowAnswerDTO();

            answerDTO.answerId = answer.getAnswerId();
            answerDTO.user = answer.getUser();
            answerDTO.question = answer.getQuestion();
            answerDTO.body = answer.getBody();
            answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
            answerDTO.rating = answer.getRating();

            return Optional.of(answerDTO);
        }

    }

    public ShowAnswerDTO getAnswerDto(Answer answer){
        ShowAnswerDTO answerDTO = new ShowAnswerDTO();

        answerDTO.answerId = answer.getAnswerId();
        answerDTO.user = answer.getUser();
        answerDTO.question = answer.getQuestion();
        answerDTO.body = answer.getBody();
        answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
        answerDTO.rating = answer.getRating();

        return answerDTO;

    }

}
