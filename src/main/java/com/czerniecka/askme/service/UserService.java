package com.czerniecka.askme.service;


import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.ShowUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    Optional<ShowUserDTO> getById(UUID userId);

    List<ShowUserDTO> getAllUsers();

    void createUser(CreateUserDTO userDTO);
}
