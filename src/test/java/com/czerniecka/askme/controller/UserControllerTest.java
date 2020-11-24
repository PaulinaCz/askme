package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    void shouldNotRegisterWhenUsernameAlreadyExists() {

        CreateUserDTO user = new CreateUserDTO();
        user.name = "name";
        user.surname = "surname";
        user.email = "username@test.com";
        user.username = "username";
        user.password = "Password123.";

        CreateUserDTO newUser = new CreateUserDTO();
        newUser.name = "Jake";
        newUser.surname = "Jo";
        newUser.email = "JakeJo@test.com";
        newUser.username = "username";
        newUser.password = "JakeJo123.";

        ResponseEntity<Void> response = userController.register(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertThrows(CustomException.class, () -> userController.register(newUser));
    }
}