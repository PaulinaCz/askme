package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerToShowAnswerDTO {

    public final QuestionToShowQuestionDTO mapper;
    public final UserToShowUserDTO userMapper;

    @Autowired
    public AnswerToShowAnswerDTO(QuestionToShowQuestionDTO mapper, UserToShowUserDTO userMapper) {
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    public Optional<ShowAnswerDTO> getOptionalAnswerDto(Optional<Answer> answerOptional){

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            Answer answer = answerOptional.get();
            ShowAnswerDTO answerDTO = new ShowAnswerDTO();

            answerDTO.answerId = answer.getAnswerId();
            answerDTO.userDto = userMapper.getUserDTO(answer.getUser());
            answerDTO.questionDTO = mapper.getQuestionDTO(answer.getQuestion());
            answerDTO.body = answer.getBody();
            answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
            answerDTO.rating = answer.getRating();

            return Optional.of(answerDTO);
        }

    }

    public ShowAnswerDTO getAnswerDto(Answer answer){
        ShowAnswerDTO answerDTO = new ShowAnswerDTO();

        answerDTO.answerId = answer.getAnswerId();
        answerDTO.userDto = userMapper.getUserDTO(answer.getUser());
        answerDTO.questionDTO = mapper.getQuestionDTO(answer.getQuestion());
        answerDTO.body = answer.getBody();
        answerDTO.dateAnswerGiven = answer.getDateAnswerGiven();
        answerDTO.rating = answer.getRating();

        return answerDTO;

    }

}
