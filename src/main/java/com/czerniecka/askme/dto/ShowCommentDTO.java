package com.czerniecka.askme.dto;

import java.time.LocalDateTime;

public class ShowCommentDTO {

    public Long commentId;
    public Long answerId;
    public Long commentingUserId;
    public String body;
    public LocalDateTime dateCommentCreated;
}
