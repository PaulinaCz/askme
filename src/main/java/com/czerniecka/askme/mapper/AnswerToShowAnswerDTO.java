package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerToShowAnswerDTO {

    public final QuestionToShowQuestionDTO mapper;

    @Autowired
    public AnswerToShowAnswerDTO(QuestionToShowQuestionDTO mapper) {
        this.mapper = mapper;
    }

    public Optional<ShowAnswerDTO> getOptionalAnswerDto(Optional<Answer> answerOptional){

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            Answer answer = answerOptional.get();
            ShowAnswerDTO answerDTO = new ShowAnswerDTO();

            answerDTO.answerId = answer.getAnswerId();
            answerDTO.username = answer.getUser().getUsername();
            answerDTO.questionId = answer.getQuestion().getQuestionId();
            answerDTO.questionDTO = mapper.getQuestionDTO(answer.getQuestion());
            answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
            answerDTO.rating = answer.getRating();

            return Optional.of(answerDTO);
        }

    }

    public ShowAnswerDTO getAnswerDto(Answer answer){
        ShowAnswerDTO answerDTO = new ShowAnswerDTO();

        answerDTO.answerId = answer.getAnswerId();
        answerDTO.username = answer.getUser().getUsername();
        answerDTO.questionId = answer.getQuestion().getQuestionId();
        answerDTO.questionDTO = mapper.getQuestionDTO(answer.getQuestion());
        answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
        answerDTO.rating = answer.getRating();

        return answerDTO;

    }

}
