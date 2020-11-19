package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.exception.CustomException;
import com.czerniecka.askme.mapper.QuestionToShowQuestionDTO;
import com.czerniecka.askme.model.Question;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.QuestionRepository;
import org.hibernate.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionToShowQuestionDTO mapper;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionToShowQuestionDTO mapper) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowQuestionDTO> getById(Long questionId) {

        Optional<Question> questionOptional = questionRepository.findById(questionId);

        if(questionOptional.isEmpty()){
            return Optional.empty();
        }else {
            return mapper.getQuestionDto(questionOptional);
        }

    }

    @Override
    public List<ShowQuestionDTO> getAll() {

        List<Question> questions = questionRepository.findAll();

        return questions.stream()
                .map(question -> mapper.getQuestionDto(Optional.of(question))
                        .orElseThrow())
                .collect(Collectors.toList());

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
                .orElseThrow(() -> new PropertyNotFoundException("Question of id " + questionId + " not found"));


        User user = (User) userDetails;

        if(questionToEdit.getUser().getUserId().equals(user.getUserId())){
            questionToEdit.setBody(questionDTO.body);
            questionToEdit.setTimeQuestionAsked(LocalDateTime.now());
            return true;
        }
        return false;

    }

    @Override
    public List<ShowQuestionDTO> getAllByUser(Long userId) {

        List<Question> questions = questionRepository.findAll();

        return questions.stream()
                .filter(question -> question.getUser().getUserId().equals(userId))
                .map(q ->mapper.getQuestionDto(Optional.of(q))
                        .orElseThrow())
                .collect(Collectors.toList());

    }

    @Override
    public void deleteQuestion(Long questionId, UserDetails userDetails) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        User user = (User) userDetails;

        if (questionOptional.isPresent()){
            Question question = questionOptional.get();
            if(question.getUser().getUserId().equals(user.getUserId())){
                questionRepository.delete(question);
            }
            throw new CustomException("This method is now allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        throw new CustomException("Question " + questionId + " not found", HttpStatus.NOT_FOUND);

    }
}
