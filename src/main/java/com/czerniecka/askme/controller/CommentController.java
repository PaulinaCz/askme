package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.ShowCommentDTO;
import com.czerniecka.askme.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    public CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ShowCommentDTO> getCommentById(@PathVariable Long commentId){

        Optional<ShowCommentDTO> comment = commentService.getById(commentId);

        return comment.map(showCommentDTO -> new ResponseEntity<>(showCommentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
