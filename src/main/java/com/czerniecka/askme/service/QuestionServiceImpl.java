package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.exception.CustomException;
import com.czerniecka.askme.model.Question;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<ShowQuestionDTO> getById(Long questionId) {

       return questionRepository.getOneByQuestionId(questionId);

    }

    @Override
    public List<ShowQuestionDTO> getAll() {

        return questionRepository.getAll();

    }

    @Override
    public Long sendQuestion(AskQuestionDTO questionDTO, UserDetails userDetails) {
        User user = (User) userDetails;
        Question questionToSend = new Question(user, questionDTO.body);

        return questionRepository.save(questionToSend).getQuestionId();
    }

    @Override
    public boolean editQuestion(Long questionId, AskQuestionDTO questionDTO, UserDetails userDetails) {

        Question questionToEdit = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Question of id " + questionId + " not found", HttpStatus.NOT_FOUND));


        if(questionToEdit.getUser().getUserId().equals(((User) userDetails).getUserId())){
            questionToEdit.setBody(questionDTO.body);
            questionToEdit.setTimeQuestionAsked(LocalDateTime.now());
            questionRepository.save(questionToEdit);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<ShowQuestionDTO> getAllByUser(User user) {

        return questionRepository.getAllByUser(user);

    }

    @Override
    public boolean deleteQuestion(Long questionId, UserDetails userDetails) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        User user = (User) userDetails;

        if (questionOptional.isPresent()){
            Question question = questionOptional.get();
            if(question.getUser().getUserId().equals(user.getUserId())){
                questionRepository.delete(question);
                return true;
            }else{
                return false;
            }
        }else{
            throw  new CustomException("Question of id " + questionId + " not found", HttpStatus.NOT_FOUND);
        }

    }
}
