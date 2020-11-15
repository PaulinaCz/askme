package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.ShowUserDTO;
import com.czerniecka.askme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return userService.login(username, password);
    }
}
