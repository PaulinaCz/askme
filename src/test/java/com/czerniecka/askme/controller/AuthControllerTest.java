package com.czerniecka.askme.controller;

import com.czerniecka.askme.TestAuthenticatedUser;
import com.czerniecka.askme.dto.LoginUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    private TestAuthenticatedUser auth;

    @Autowired
    private AuthController authController;

    @Test
    void shouldReturnTokenWhenAuthenticationPassed(){

        String username = "username";
        String password = "Password123.";

        auth.authenticatedUser(username, password);
        LoginUserDTO login = new LoginUserDTO();
        login.setUsername(username);
        login.setPassword(password);

        ResponseEntity<String> response = authController.login(login);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());

    }
}