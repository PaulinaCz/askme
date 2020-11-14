package com.czerniecka.askme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private LocalDateTime dateCreated;

    public User(String name, String surname, String email, String password, String username) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        if(username.isEmpty()){
            this.username = name + surname;
        }else{
            this.username = username;
        }
        this.dateCreated = LocalDateTime.now();
    }
}
