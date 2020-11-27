package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    @Override
    public boolean register(CreateUserDTO userDTO){
        String hashedPassword = passwordEncoder.encode(userDTO.password);

        Optional<User> notUniqueUsername = userRepository.findByUsername(userDTO.username);
        if(notUniqueUsername.isPresent())
        {
            return false;
        }else {
            User user = new User(userDTO.name, userDTO.surname, userDTO.username, userDTO.email, hashedPassword);
            userRepository.save(user);
            return true;
        }
    }
}
