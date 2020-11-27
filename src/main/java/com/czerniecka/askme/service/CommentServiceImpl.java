package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.dto.WriteCommentDTO;
import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.model.Comment;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.AnswerRepository;
import com.czerniecka.askme.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Optional<ShowCommentDTO> getById(Long commentId) {

        Optional<ShowCommentDTO> commentOptional = commentRepository.getOneByCommentId(commentId);
        return commentOptional;
    }

    @Override
    public Long addComment(WriteCommentDTO writeCommentDTO, Long answerId, UserDetails userDetails) {

        User user = (User) userDetails;
        Comment comment = new Comment(user, writeCommentDTO.body);
        Optional<Answer> answer = answerRepository.findById(answerId);
        if(answer.isPresent()){
            answer.get().addComment(comment);
            return commentRepository.save(comment).getCommentId();
        }

        return -1L;
    }

    @Override
    public List<ShowCommentDTO> getAllByAnswerId(Long answerId) {

        return commentRepository.getAllByAnswerId(answerId);

    }

    @Override
    public List<ShowCommentDTO> getAllByUser(Long userId) {

       return commentRepository.getAllByUser(userId);

    }
}
