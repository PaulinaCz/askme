package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.dto.WriteCommentDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {

    Optional<ShowCommentDTO> getById(Long commentId);

    Long addComment(WriteCommentDTO writeCommentDTO, Long answerId, UserDetails userDetails);

    List<ShowCommentDTO> getAllByAnswerId(Long answerId);

    List<ShowCommentDTO> getAllByUser(Long userId);


}
