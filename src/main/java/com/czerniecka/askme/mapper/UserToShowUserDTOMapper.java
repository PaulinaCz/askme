package com.czerniecka.askme.mapper;

import com.czerniecka.askme.dto.ShowUserDTO;
import com.czerniecka.askme.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserToShowUserDTOMapper {

    public Optional<ShowUserDTO> getUserDto(Optional<User> userOptional){

        if(userOptional.isEmpty()){
            return Optional.empty();
        }else{
            User user = userOptional.get();

            ShowUserDTO userDTO = new ShowUserDTO();

            userDTO.userId = user.getUserId();
            userDTO.name = user.getName();
            userDTO.surname = user.getName();
            userDTO.email = user.getEmail();
            userDTO.password = user.getPassword();
            userDTO.username = user.getUsername();
            userDTO.dateCreated = user.getDateCreated();
            return Optional.of(userDTO);
        }

    }

}
