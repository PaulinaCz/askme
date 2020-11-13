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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private Long userId;
    private String body;
    private LocalDateTime timeQuestionAsked;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId")
    private List<Answer> answers;

    public Question(Long userId, String body){
        this.userId = userId;
        this.body = body;
        this.timeQuestionAsked = LocalDateTime.now();
        this.answers = new ArrayList<>();
    }

}
