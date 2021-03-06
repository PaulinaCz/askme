package com.czerniecka.askme.repository;

import com.czerniecka.askme.dto.ShowAnswerDTO;
import com.czerniecka.askme.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT a From Answer a where a.question.questionId = ?1 ORDER BY a.dateAnswerGiven DESC ")
    List<ShowAnswerDTO> getAllByQuestionId(Long questionId);

    Optional<ShowAnswerDTO> getOneByAnswerId(Long answerId);

    List<ShowAnswerDTO> findAllByUser(Long userId);
}
