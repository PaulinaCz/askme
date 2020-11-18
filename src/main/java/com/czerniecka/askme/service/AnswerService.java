package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Rating;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnswerService {
    Optional<ShowAnswerDTO> getById(Long answerId);

    Long addAnswer(AnswerDTO answerDTO, Long questionId, UserDetails userDetails);

    List<ShowAnswerDTO> getAllByQuestionId(Long questionId);

    boolean changeRating(Long answerId, Rating rating);
}
