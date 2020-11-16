package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.LoginUserDTO;

public interface MyUserDetailsService {

    void registerUser(CreateUserDTO userDTO);

    void loginUser(LoginUserDTO loginUserDTO);


}
