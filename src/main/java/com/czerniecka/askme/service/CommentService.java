package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.ShowCommentDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommentService {

    Optional<ShowCommentDTO> getById(Long commentId);
}
