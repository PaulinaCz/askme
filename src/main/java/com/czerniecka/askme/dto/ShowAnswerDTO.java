package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public interface ShowAnswerDTO {

    Long getAnswerId();
    ShowUserDto getUser();
    ShowQuestionDTO getQuestion();
    String getBody();
    LocalDateTime getDateAnswerGiven();
    Long getRating();

}
