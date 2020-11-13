package com.czerniecka.askme.dto;

import com.czerniecka.askme.model.User;

import java.time.LocalDateTime;

public class ShowQuestionDTO {

    public Long questionId;
    public User user;
    public String body;
    public LocalDateTime timeQuestionAsked;
}
