package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public class ShowCommentDTO {

    public Long commentId;
    public ShowUserDto userDto;
    public String body;
    public LocalDateTime dateCommentCreated;
}
