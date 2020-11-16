package com.czerniecka.askme.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String hashPassword(String password){
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

}
