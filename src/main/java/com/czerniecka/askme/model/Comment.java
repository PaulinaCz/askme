package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long commentId;
    private Long answerId;
    private Long commentingUserId;
    private String body;
    private LocalDateTime dateCommentCreated;

    public Comment(Long answerId, Long commentingUserId, String body) {
        this.answerId = answerId;
        this.commentingUserId = commentingUserId;
        this.body = body;
        this.dateCommentCreated = LocalDateTime.now();
    }
}
