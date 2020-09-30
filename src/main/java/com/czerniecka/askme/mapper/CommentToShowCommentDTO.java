package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.model.Comment;

import java.util.Optional;

public class CommentToShowCommentDTO {

    Optional<ShowCommentDTO> getCommentDto(Optional<Comment> optionalComment){

        if(optionalComment.isEmpty()){
            return Optional.empty();
        }else {
            Comment comment = optionalComment.get();

            ShowCommentDTO commentDTO = new ShowCommentDTO();

            commentDTO.commentId = comment.getCommentId();
            commentDTO.answerId = comment.getAnswerId();
            commentDTO.commentingUserId = comment.getCommentingUserId();
            commentDTO.body = comment.getBody();
            commentDTO.dateCommentCreated = comment.getDateCommentCreated();

            return Optional.of(commentDTO);
        }

    }

}
