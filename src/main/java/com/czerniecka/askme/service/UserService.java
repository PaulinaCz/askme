package com.czerniecka.askme.service;


import com.czerniecka.askme.dto.CreateUserDTO;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    String createUser(CreateUserDTO userDTO);

    String login(String username, String password);
}
