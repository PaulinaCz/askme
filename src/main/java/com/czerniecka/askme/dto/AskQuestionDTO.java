package com.czerniecka.askme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AskQuestionDTO {

    @NotBlank(message = "Please add content to your question")
    @Size(max = 5000, message = "Question should be up to 5000 characters!")
    public String body;
}
