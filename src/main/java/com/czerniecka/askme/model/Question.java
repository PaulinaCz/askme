package com.czerniecka.askme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    private User user;
    private String body;
    private LocalDateTime timeQuestionAsked;

    @OneToMany(mappedBy = "question",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answers;

    public Question(User user, String body){
        this.user = user;
        this.body = body;
        this.timeQuestionAsked = LocalDateTime.now();
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
        answer.setQuestion(this);
    }

}
