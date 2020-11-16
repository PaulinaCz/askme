/*
package com.czerniecka.askme.service;


import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.LoginUserDTO;
import com.czerniecka.askme.exception.CustomException;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import com.czerniecka.askme.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, MyUserDetailsService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MyUserDetailsServiceImpl(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username){

        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

    }

    @Override
    public void loginUser(LoginUserDTO loginUserDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(),
                        loginUserDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        MyUserDetailsServiceImpl userDetails = (MyUserDetailsServiceImpl) authentication.getPrincipal();

    }

    @Override
    public void registerUser(CreateUserDTO userDTO) {
        if(!userRepository.existsUserByUsername(userDTO.email)){
            User user = new User(userDTO.name, userDTO.surname,userDTO.username, userDTO.email, passwordEncoder.encode(userDTO.password));
            userRepository.save(user);
        }
        else{
            throw new CustomException("Username already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
*/
