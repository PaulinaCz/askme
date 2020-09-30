package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Answer {

    @Id
    private UUID answerId;
    private UUID answeringUserId;
    private UUID questionId;
    private String body;
    private LocalDateTime dateAnswerGiven;
    private Rating rating;

    public Answer(UUID answeringUserId, UUID questionId, String body, Rating rating) {
        this.answerId = UUID.randomUUID();
        this.answeringUserId = answeringUserId;
        this.questionId = questionId;
        this.body = body;
        this.dateAnswerGiven = LocalDateTime.now();
        this.rating = rating;
    }
}
