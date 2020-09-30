package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    private UUID userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private LocalDateTime dateCreated;

    public User(String name, String surname, String email, String password, String username) {
        userId = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.dateCreated = LocalDateTime.now();
    }
}
