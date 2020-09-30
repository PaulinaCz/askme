package com.czerniecka.askme.dto;

import com.czerniecka.askme.model.Rating;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowAnswerDTO {

    public UUID answerId;
    public UUID answeringUserId;
    public UUID questionId;
    public String body;
    public LocalDateTime dateAnswerGiven;
    public Rating rating;

}
