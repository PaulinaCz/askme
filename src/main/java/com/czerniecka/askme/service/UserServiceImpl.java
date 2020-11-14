package com.czerniecka.askme.service;

import com.czerniecka.askme.dto.CreateUserDTO;
import com.czerniecka.askme.dto.ShowUserDTO;
import com.czerniecka.askme.mapper.UserToShowUserDTO;
import com.czerniecka.askme.model.User;
import com.czerniecka.askme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserToShowUserDTO mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserToShowUserDTO mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ShowUserDTO> getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            return Optional.empty();
        } else {
            return mapper.getUserDto(user);
        }
    }

    @Override
    public List<ShowUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> mapper.getUserDto(Optional.of(user)).
                        orElseThrow())
                .collect(Collectors.toList());

    }

    @Override
    public void createUser(CreateUserDTO userDTO) {

        User user = new User(userDTO.name, userDTO.surname, userDTO.email, userDTO.password, userDTO.username);

        userRepository.save(user);

    }
}
