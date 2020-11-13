package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Long fromUser;
    private String body;
    private LocalDateTime timeQuestionAsked;

    public Question(Long fromUser, String body){
        this.fromUser = fromUser;
        this.body = body;
        this.timeQuestionAsked = LocalDateTime.now();
    }

}
