package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.RatingDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.exception.CustomException;
import com.czerniecka.askme.mapper.AnswerToShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.model.Question;
import com.czerniecka.askme.model.Rating;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.AnswerRepository;
import com.czerniecka.askme.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerToShowAnswerDTO mapper;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository, AnswerToShowAnswerDTO mapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowAnswerDTO> getById(Long answerId) {

        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if(answerOptional.isEmpty()){
            return Optional.empty();
        }else{
            return mapper.getOptionalAnswerDto(answerOptional);
        }

    }

    @Override
    public List<ShowAnswerDTO> getAllByQuestionId(Long questionId) {

        List<Answer> answers = answerRepository.getAllByQuestionId(questionId);

        return answers.stream().map(mapper::getAnswerDto).collect(Collectors.toList());

    }

    @Override
    public boolean changeRating(Long answerId, RatingDTO rating) {

        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        Rating r = null;
        if(rating.rate == 1){
            r = Rating.USEFUL;
        }else if(rating.rate == -1){
            r = Rating.NOTUSEFUL;
        }

        if(answerOptional.isPresent()){
            Answer answer = answerOptional.get();
            Long currentRate = answer.getRating();
            answer.setRating(currentRate + r.getRate());
            answerRepository.save(answer);
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public Long addAnswer(AnswerDTO answerDTO, Long questionId, UserDetails userDetails) {

        User user = (User) userDetails;
        Answer answer = new Answer(user, answerDTO.body);
        Optional<Question> question = questionRepository.findById(questionId);
        if(question.isPresent()){
            question.get().addAnswer(answer);
            return answerRepository.save(answer).getAnswerId();
        }

        return -1L;
    }

    @Override
    public boolean editAnswer(Long answerId, AnswerDTO answerDTO, UserDetails userDetails) {

        Answer answerToEdit = answerRepository.findById(answerId)
                .orElseThrow(() -> new CustomException("Answer of id " + answerId + " not found", HttpStatus.NOT_FOUND));

        User user = (User) userDetails;

        if(answerToEdit.getUser().getUserId().equals(user.getUserId())){
            answerToEdit.setBody(answerDTO.body);
            answerToEdit.setDateAnswerGiven(LocalDateTime.now());
            answerRepository.save(answerToEdit);
            return true;
        }
        return false;

    }

    @Override
    public List<ShowAnswerDTO> getAllByUser(Long userId) {
        List<Answer> answers = answerRepository.findAll();

        return answers.stream()
                .filter(answer -> answer.getUser().getUserId().equals(userId))
                .map(mapper::getAnswerDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAnswer(Long answerId, UserDetails userDetails) {
        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        User user = (User) userDetails;

        if (answerOptional.isPresent()){
            Answer answer = answerOptional.get();
            if(answer.getUser().getUserId().equals(user.getUserId())){
                answerRepository.delete(answer);
                return true;
            }else{
                throw new CustomException("This method is now allowed", HttpStatus.METHOD_NOT_ALLOWED);
            }

        }else{
            return false;
        }

    }

}
