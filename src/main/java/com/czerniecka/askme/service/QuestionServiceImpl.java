package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.mapper.QuestionToShowQuestionDTO;
import com.czerniecka.askme.model.Question;
import com.czerniecka.askme.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionToShowQuestionDTO mapper;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionToShowQuestionDTO mapper) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowQuestionDTO> getById(UUID questionId) {
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
                .map(question -> mapper.getQuestionDto(Optional.of(question)).get())
                .collect(Collectors.toList());

    }

    @Override
    public void sendQuestion(AskQuestionDTO questionDTO) {
        Question questionToSend = new Question(questionDTO.fromUser, questionDTO.body);

        questionRepository.save(questionToSend);
    }

    @Override
    public void editQuestion(UUID questionId, AskQuestionDTO questionDTO) {

        Question questionToEdit = questionRepository.findById(questionId).get();

        questionToEdit.setBody(questionDTO.body);
        questionToEdit.setTimeQuestionAsked(LocalDateTime.now());

    }
}