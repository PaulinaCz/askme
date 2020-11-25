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
public class Answer {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "answer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
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

    public void addComment(Comment comment){

        this.comments.add(comment);
        comment.setAnswer(this);
    }

}
