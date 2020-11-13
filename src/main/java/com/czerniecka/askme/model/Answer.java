package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private Long questionId;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answerId")
    private List<Comment> comments;
    private String body;
    private LocalDateTime dateAnswerGiven;
    private Long rating;

    public Answer(Long questionId, Long userId, String body) {
        this.questionId = questionId;
        this.userId = userId;
        this.body = body;
        this.dateAnswerGiven = LocalDateTime.now();
        this.rating = 0L;
        this.comments = new ArrayList<>();
    }

}
