package com.czerniecka.askme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowQuestionDTO {

    public UUID questionId;
    public UUID fromUser;
    public String body;
    public LocalDateTime timeQuestionAsked;
}
