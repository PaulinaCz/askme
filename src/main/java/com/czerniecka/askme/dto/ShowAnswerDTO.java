package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public class ShowAnswerDTO {

    public Long answerId;
    public ShowUserDto userDto;
    public ShowQuestionDTO questionDTO;
    public String body;
    public LocalDateTime dateAnswerGiven;
    public Long rating;

}
