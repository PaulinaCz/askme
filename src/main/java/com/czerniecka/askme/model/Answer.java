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
public class Answer {

    @Id
    @GeneratedValue
    private Long answerId;
    private Long answeringUserId;
    private Long questionId;
    private String body;
    private LocalDateTime dateAnswerGiven;
    private Rating rating;

    public Answer(Long answeringUserId, Long questionId, String body, Rating rating) {
        this.answeringUserId = answeringUserId;
        this.questionId = questionId;
        this.body = body;
        this.dateAnswerGiven = LocalDateTime.now();
        this.rating = rating;
    }
}
