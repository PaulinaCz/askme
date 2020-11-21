package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public class ShowAnswerDTO {

    public Long answerId;
    public String username;
    public Long questionId;
    public ShowQuestionDTO questionDTO;
    public LocalDateTime dateAnswerGiven;
    public Long rating;

}
