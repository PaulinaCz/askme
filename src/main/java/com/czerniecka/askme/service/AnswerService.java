package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AnswerDTO;
import com.czerniecka.askme.dto.ShowAnswerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnswerService {
    Optional<ShowAnswerDTO> getById(Long answerId);

    Long addAnswer(AnswerDTO answerDTO, Long questionId);

    List<ShowAnswerDTO> getAllByQuestionId(Long questionId);
}
