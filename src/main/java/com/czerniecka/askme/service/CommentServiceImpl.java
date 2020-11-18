package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.dto.WriteCommentDTO;
import com.czerniecka.askme.mapper.CommentToShowCommentDTO;
import com.czerniecka.askme.model.Comment;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentToShowCommentDTO mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentToShowCommentDTO mapper) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowCommentDTO> getById(Long commentId) {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isEmpty()){
            return Optional.empty();
        }else{
            return mapper.getOptionalCommentDto(commentOptional);
        }

    }

    @Override
    public Long addComment(WriteCommentDTO writeCommentDTO, Long answerId, User user) {
        Comment comment = new Comment(user, writeCommentDTO.body);

        return commentRepository.save(comment).getCommentId();
    }

    @Override
    public List<ShowCommentDTO> getAllByAnswerId(Long answerId) {

        List<Comment> comments = commentRepository.getAllByAnswerId(answerId);

        if(comments.isEmpty()){
            return Collections.emptyList();
        }
        return comments.stream().map(mapper::getCommentDto).collect(Collectors.toList());
    }
}
