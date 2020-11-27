package com.czerniecka.askme.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AnswerDTO {

    @NotBlank(message = "Please add content to your answer")
    @Size(max = 5000, message = "Answer should be up to 5000 characters!")
    public String body;


}
