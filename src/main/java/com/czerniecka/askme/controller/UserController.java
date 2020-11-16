package com.czerniecka.askme.controller;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.LoginUserDTO;
import com.czerniecka.askme.repository.UserRepository;
import com.czerniecka.askme.security.JwtTokenProvider;
import com.czerniecka.askme.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    private MyUserDetailsService userService;

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider jwtTokenProvider;

    public UserController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public UserController(MyUserDetailsService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO userDTO){
        userService.registerUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestParam LoginUserDTO loginUserDTO){
        userService.loginUser(loginUserDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
