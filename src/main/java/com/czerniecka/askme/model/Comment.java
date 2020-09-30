package com.czerniecka.askme.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Comment {

    @Id
    private UUID commentId;
    private UUID answerId;
    private UUID commentingUserId;
    private String body;
    private LocalDateTime dateCommentCreated;

    public Comment(UUID answerId, UUID commentingUserId, String body) {
        this.commentId = UUID.randomUUID();
        this.answerId = answerId;
        this.commentingUserId = commentingUserId;
        this.body = body;
        this.dateCommentCreated = LocalDateTime.now();
    }
}
