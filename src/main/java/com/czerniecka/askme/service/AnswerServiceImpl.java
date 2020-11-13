package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.mapper.AnswerToShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService{

    public AnswerRepository answerRepository;
    public AnswerToShowAnswerDTO mapper;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerToShowAnswerDTO mapper) {
        this.answerRepository = answerRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowAnswerDTO> getById(Long answerId) {

        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            return mapper.getAnswerDto(answerOptional);
        }

    }

    @Override
    public List<ShowAnswerDTO> getAllByQuestionId(Long questionId) {

        List<Answer> answers = answerRepository.findAll();

        return answers.stream().filter(answer -> answer.getQuestionId().equals(questionId))
                .map(a -> mapper.getAnswerDto(Optional.of(a)).orElseThrow())
                .collect(Collectors.toList());

    }

    @Override
    public Long addAnswer(AnswerDTO answerDTO, Long questionId) {

        Answer answer = new Answer(answerDTO.answeringUserId, questionId, answerDTO.body, answerDTO.rating);

        return answerRepository.save(answer).getAnswerId();

    }


}
