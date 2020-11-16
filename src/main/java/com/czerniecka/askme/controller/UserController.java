package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {


    private CustomUserDetailsService userService;

    @Autowired
    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Void> register(@RequestBody CreateUserDTO userDTO){
        userService.register(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<String> login(@RequestBody LoginUserDTO userDTO){
//        String token = userService.login(userDTO);
//        return ResponseEntity.ok(token);
//    }
}

