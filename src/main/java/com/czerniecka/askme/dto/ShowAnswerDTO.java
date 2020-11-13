package com.czerniecka.askme.dto;

import com.czerniecka.askme.model.User;

import java.time.LocalDateTime;

public class ShowAnswerDTO {

    public Long answerId;
    public User user;
    public Long questionId;
    public String body;
    public LocalDateTime dateAnswerGiven;
    public Long rating;

}
