package com.czerniecka.askme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowUserDTO {

    public UUID userId;
    public String name;
    public String surname;
    public String email;
    public String password;
    public String username;
    public LocalDateTime dateCreated;

}
