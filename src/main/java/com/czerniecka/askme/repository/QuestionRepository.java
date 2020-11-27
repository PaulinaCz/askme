package com.czerniecka.askme.repository;

import com.czerniecka.askme.dto.ShowQuestionDTO;
import com.czerniecka.askme.model.Question;
import com.czerniecka.askme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query(value = "SELECT q FROM Question as q")
    List<ShowQuestionDTO> getAll();

    List<ShowQuestionDTO> getAllByUser(User user);

    Optional<ShowQuestionDTO> getOneByQuestionId(Long questionId);
}
