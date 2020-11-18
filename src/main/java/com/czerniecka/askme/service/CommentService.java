package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.dto.WriteCommentDTO;
import com.czerniecka.askme.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommentService {

    Optional<ShowCommentDTO> getById(Long commentId);

    Long addComment(WriteCommentDTO writeCommentDTO, Long answerId, User user);
}
