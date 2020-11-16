package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.CreateUserDTO;

public interface UserService {

    void register(CreateUserDTO userDTO);

}
