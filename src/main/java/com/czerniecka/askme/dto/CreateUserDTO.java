package com.czerniecka.askme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateUserDTO {

    @NotBlank(message = "Please provide a name")
    @Size(min = 3, message = "Name must be longer than 3 characters")
    public String name;

    @NotBlank(message = "Please provide a surname")
    @Size(min = 3, message = "Surname must be longer than 3 characters")
    public String surname;

    @NotBlank(message = "Please provide username")
    @Size(min = 3, message = "Username must be longer than 3 characters")
    public String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid")
    public String email;

    @NotBlank(message = "Please enter your password")
    @Size(min = 8, max = 15, message = "Your password must have between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^&*])(?=\\S+$).{8,15}$",
            message = "Password must be between 8 and 15 characters, contain at least one digit," +
                    "at least one lower case, at least one upper case and one special character")
    public String password;
}
