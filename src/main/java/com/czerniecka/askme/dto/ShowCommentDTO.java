package com.czerniecka.askme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowCommentDTO {

    public UUID commentId;
    public UUID answerId;
    public UUID commentingUserId;
    public String body;
    public LocalDateTime dateCommentCreated;
}
