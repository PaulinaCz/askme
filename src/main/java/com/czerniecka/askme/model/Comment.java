package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    private User user;
    private String body;
    private LocalDateTime dateCommentCreated;

    public Comment(User user, String body) {
        this.user = user;
        this.body = body;
        this.dateCommentCreated = LocalDateTime.now();
    }
}
