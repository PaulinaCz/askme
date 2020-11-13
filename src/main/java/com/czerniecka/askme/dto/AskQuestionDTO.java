package com.czerniecka.askme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AskQuestionDTO {

    @NotNull(message = "Question not asked. Try again")
    public Long userId;
    @NotBlank(message = "Please add content to your question")
    @Size(max = 5000, message = "Question should be up to 5000 characters!")
    public String body;
}
