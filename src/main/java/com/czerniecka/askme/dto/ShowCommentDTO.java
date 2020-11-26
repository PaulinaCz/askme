package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public interface ShowCommentDTO {

    Long getCommentId();
    ShowUserDto getUser();
    String getBody();
    LocalDateTime getDateCommentCreated();
}
