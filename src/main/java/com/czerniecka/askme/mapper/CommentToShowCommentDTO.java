//package com.czerniecka.askme.mapper;
//
//import com.czerniecka.askme.dto.ShowCommentDTO;
//import com.czerniecka.askme.model.Comment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CommentToShowCommentDTO {
//
//    public final UserToShowUserDTO mapper;
//
//    @Autowired
//    public CommentToShowCommentDTO(UserToShowUserDTO mapper) {
//        this.mapper = mapper;
//    }
//
//    public Optional<ShowCommentDTO> getOptionalCommentDto(Optional<Comment> optionalComment){
//
//        if(optionalComment.isEmpty()){
//            return Optional.empty();
//        }else {
//            Comment comment = optionalComment.get();
//
//            ShowCommentDTO commentDTO = new ShowCommentDTO();
//
//            commentDTO.commentId = comment.getCommentId();
//            commentDTO.userDto = mapper.getUserDTO(comment.getUser());
//            commentDTO.body = comment.getBody();
//            commentDTO.dateCommentCreated = comment.getDateCommentCreated();
//
//            return Optional.of(commentDTO);
//        }
//
//    }
//
//    public ShowCommentDTO getCommentDto(Comment comment){
//        ShowCommentDTO commentDTO = new ShowCommentDTO();
//
//        commentDTO.commentId = comment.getCommentId();
//        commentDTO.userDto = mapper.getUserDTO(comment.getUser());
//        commentDTO.body = comment.getBody();
//        commentDTO.dateCommentCreated = comment.getDateCommentCreated();
//
//        return commentDTO;
//    }
//
//}
