package com.czerniecka.askme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WriteCommentDTO {

    @NotBlank(message = "Cannot send empty comment")
    @Size(max = 500, message = "Comment should be up to 500 characters!")
    public String body;
}
