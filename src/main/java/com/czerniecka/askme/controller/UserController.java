package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.ShowUserDTO;
import com.czerniecka.askme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShowUserDTO> getUserById(@PathVariable UUID userId){
        Optional<ShowUserDTO> user = userService.getById(userId);
        return user.map(showUserDTO -> new ResponseEntity<>(showUserDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<ShowUserDTO>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody CreateUserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
