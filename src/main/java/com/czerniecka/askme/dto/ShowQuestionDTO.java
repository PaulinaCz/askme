package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public class ShowQuestionDTO {

    public Long questionId;
    public Long fromUser;
    public String body;
    public LocalDateTime timeQuestionAsked;
}
