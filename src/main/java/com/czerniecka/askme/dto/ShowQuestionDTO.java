package com.czerniecka.askme.dto;


import java.time.LocalDateTime;

public interface ShowQuestionDTO {

    Long getQuestionId();

    ShowUserDto getUser();

    String getBody();

    LocalDateTime getTimeQuestionAsked();

}
