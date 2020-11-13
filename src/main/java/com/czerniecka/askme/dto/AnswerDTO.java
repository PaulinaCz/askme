package com.czerniecka.askme.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerDTO {

    @NotNull(message = "Could not answer. Try again")
    public Long userId;
    @NotNull(message = "Choose question you want to answer")
    public Long questionId;
    @NotBlank(message = "Please add content to your answer")
    @Size(max = 5000, message = "Answer should be up to 5000 characters!")
    public String body;

}
