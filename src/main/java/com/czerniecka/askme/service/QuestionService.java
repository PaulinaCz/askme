package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.AskQuestionDTO;
import com.czerniecka.askme.dto.ShowQuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface QuestionService {
    Optional<ShowQuestionDTO> getById(UUID questionId);

    List<ShowQuestionDTO> getAll();

    void sendQuestion(AskQuestionDTO questionDTO);

    void editQuestion(UUID questionId, AskQuestionDTO questionDTO);
}
