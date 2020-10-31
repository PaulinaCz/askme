package com.czerniecka.askme.dto;

import com.czerniecka.askme.model.Rating;

import java.time.LocalDateTime;

public class ShowAnswerDTO {

    public Long answerId;
    public Long answeringUserId;
    public Long questionId;
    public String body;
    public LocalDateTime dateAnswerGiven;
    public Rating rating;

}
