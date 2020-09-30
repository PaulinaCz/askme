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
public class Question {

    @Id
    private UUID questionId;
    private UUID fromUser;
    private String body;
    private LocalDateTime timeQuestionAsked;

    public Question(UUID fromUser, String body){
        questionId = UUID.randomUUID();
        this.fromUser = fromUser;
        this.body = body;
        this.timeQuestionAsked = LocalDateTime.now();
    }

}
