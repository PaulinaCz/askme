package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowUserDto;
import com.czerniecka.askme.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserToShowUserDTO {

    public ShowUserDto getUserDTO(User user){

        ShowUserDto userDto = new ShowUserDto();

        userDto.userId = user.getUserId();
        userDto.username = user.getUsername();

        return userDto;

    }


}
