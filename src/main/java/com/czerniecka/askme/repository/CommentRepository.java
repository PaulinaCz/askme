package com.czerniecka.askme.repository;

import com.czerniecka.askme.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c From Comment c where c.answer.answerId = ?1 ORDER BY c.dateCommentCreated DESC ")
    List<Comment> getAllByAnswerId(Long answerId);

    @Query(value = "SELECT c From Comment c where c.user.userId = ?1 ORDER BY c.dateCommentCreated DESC")
    List<Comment> getAllByUser(Long userId);

}
