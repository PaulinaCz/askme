package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionService {
    Optional<ShowQuestionDTO> getById(Long questionId);

    List<ShowQuestionDTO> getAll();

    Long sendQuestion(AskQuestionDTO questionDTO, UserDetails userDetails);

    boolean editQuestion(Long questionId, AskQuestionDTO questionDTO, UserDetails userDetails);

    List<ShowQuestionDTO> getAllByUser(Long userId);

    boolean deleteQuestion(Long questionId, UserDetails userDetails);
}
