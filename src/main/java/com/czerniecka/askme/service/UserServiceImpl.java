package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.exception.CustomException;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import com.czerniecka.askme.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String createUser(CreateUserDTO userDTO) {

        if(!userRepository.existsUserByUsername(userDTO.email)){
            User user = new User(userDTO.name, userDTO.surname,userDTO.username, userDTO.email, passwordEncoder.encode(userDTO.password));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail(), Collections.singletonList("USER"));
        }
        else{
            throw new CustomException("Username already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @Override
    public String login(String username, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, Collections.singletonList("USER"));
        } catch(AuthenticationException e){
            throw new CustomException("Invalid username/password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
