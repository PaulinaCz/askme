package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {


    private CustomUserDetailsService userService;

    @Autowired
    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody CreateUserDTO userDTO){
        if(!userService.register(userDTO)){
            return new ResponseEntity("User " + userDTO.username + " already exists", HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

}

