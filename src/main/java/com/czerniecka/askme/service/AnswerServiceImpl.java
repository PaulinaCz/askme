package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.RatingDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.exception.CustomException;
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


@Service
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<ShowAnswerDTO> getById(Long answerId) {

        return answerRepository.getOneByAnswerId(answerId);

    }

    @Override
    public List<ShowAnswerDTO> getAllByQuestionId(Long questionId) {

        return answerRepository.getAllByQuestionId(questionId);

    }

    @Override
    public boolean changeRating(Long answerId, RatingDTO rating) {

        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if(rating.rate == 1){
            rating.rate = Rating.USEFUL.getRate();
        }else if(rating.rate == -1){
            rating.rate = Rating.NOTUSEFUL.getRate();
        }

        if(answerOptional.isPresent()){
            Answer answer = answerOptional.get();
            Long currentRate = answer.getRating();
            answer.setRating(currentRate + rating.rate);
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

        List<ShowAnswerDTO> answers = answerRepository.findAllByUser(userId);

        return answers;

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
                throw new CustomException("Can only delete your answer", HttpStatus.BAD_REQUEST);
            }
        }else{
            return false;
        }

    }

}
