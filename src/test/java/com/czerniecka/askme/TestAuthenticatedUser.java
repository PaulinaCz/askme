package com.czerniecka.askme;

import com.czerniecka.askme.controller.UserController;
import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAuthenticatedUser {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;


    public User authenticatedUser(String username, String password){
        CreateUserDTO createUser = new CreateUserDTO();
        createUser.name = "name";
        createUser.surname = "surname";
        createUser.email = username + "@test.uk";
        createUser.username = username;
        createUser.password = password;

        userController.register(createUser);

        return userRepository.findByUsername(username).get();
    }
}
