package com.czerniecka.askme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AskQuestionDTO {

    public UUID fromUser;
    public String body;
    public LocalDateTime timeQuestionAsked;
}
