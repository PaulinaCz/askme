package com.czerniecka.askme.dto;

import com.czerniecka.askme.model.Answer;
import com.czerniecka.askme.model.User;

import java.time.LocalDateTime;

public class ShowCommentDTO {

    public Long commentId;
    public User user;
    public String body;
    public LocalDateTime dateCommentCreated;
}
