package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long userId;
    private String body;
    private LocalDateTime dateCommentCreated;

    public Comment(Long userId, String body) {
        this.userId = userId;
        this.body = body;
        this.dateCommentCreated = LocalDateTime.now();
    }
}
