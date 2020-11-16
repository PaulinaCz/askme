package com.czerniecka.askme.security;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.LoginUserDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import com.czerniecka.askme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private PasswordService passwordService;
//    private AuthenticationManager authenticationManager;
//    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    @Override
    public void register(CreateUserDTO userDTO){
        String hashedPassword = passwordService.hashPassword(userDTO.password);
        User user = new User(userDTO.name, userDTO.surname, userDTO.username, userDTO.email, hashedPassword);
        userRepository.save(user);
    }
//
//    @Override
//    public String login(LoginUserDTO data){
//            String username = data.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword() ));
//        String token = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).orElseThrow().getRoles());
//        return token;
//    }
}
