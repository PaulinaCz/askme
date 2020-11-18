package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.dto.WriteCommentDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    public CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<ShowCommentDTO> getCommentById(@PathVariable Long commentId){

        Optional<ShowCommentDTO> comment = commentService.getById(commentId);

        return comment.map(showCommentDTO -> new ResponseEntity<>(showCommentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/{answerId}/comments")
    public ResponseEntity<List<ShowCommentDTO>> getAllCommentByAnswerId(@PathVariable Long answerId){

        List<ShowCommentDTO> comments = commentService.getAllByAnswerId(answerId);

        if(comments.isEmpty()){
            return new ResponseEntity("No comments for answer " + answerId + " found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

    @PostMapping("/{questionId}/answers/{answerId}/comment")
   public ResponseEntity<Long> addComment(@Valid @RequestBody WriteCommentDTO writeCommentDTO,
                                                     @PathVariable Long answerId,
                                                     @PathVariable Long questionId,
                                                      UserDetails userDetails
                                                     ){

        Long commentId = commentService.addComment(writeCommentDTO, questionId, userDetails);

        return new ResponseEntity<>(commentId, HttpStatus.CREATED);

   }
}
