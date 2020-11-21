package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionToShowQuestionDTO {

    public final UserToShowUserDTO mapper;

    @Autowired
    public QuestionToShowQuestionDTO(UserToShowUserDTO mapper) {
        this.mapper = mapper;
    }

    public Optional<ShowQuestionDTO> getOptionalQuestionDto(Optional<Question> questionOptional){

        if(questionOptional.isEmpty()){
            return Optional.empty();
        }else{

            Question question = questionOptional.get();

            ShowQuestionDTO questionDTO = new ShowQuestionDTO();

            questionDTO.questionId = question.getQuestionId();
            questionDTO.userDto = mapper.getUserDTO(question.getUser());
            questionDTO.body = question.getBody();
            questionDTO.timeQuestionAsked = question.getTimeQuestionAsked();

            return Optional.of(questionDTO);

        }

    }

    public ShowQuestionDTO getQuestionDTO(Question question){

        ShowQuestionDTO questionDTO = new ShowQuestionDTO();

        questionDTO.questionId = question.getQuestionId();
        questionDTO.userDto = mapper.getUserDTO(question.getUser());
        questionDTO.body = question.getBody();
        questionDTO.timeQuestionAsked = question.getTimeQuestionAsked();

        return questionDTO;

    }

}
