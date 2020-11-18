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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "answer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> comments;
    private String body;
    private LocalDateTime dateAnswerGiven;
    private Long rating;

    public Answer(User user, String body) {
        this.user = user;
        this.body = body;
        this.dateAnswerGiven = LocalDateTime.now();
        this.rating = 0L;
        this.comments = new ArrayList<>();
    }

}
