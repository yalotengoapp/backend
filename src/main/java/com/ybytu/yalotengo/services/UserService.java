package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.dtos.UserMapper;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public UserService(UserRepository userRepository, ItemRepository itemRepository){
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public UserResponse addUser(UserRequest userRequest){
        User newUser = UserMapper.dtoToEntity(userRequest);
        newUser.setPassword(userRequest.password());
        User savedUser = userRepository.save(newUser);
        return UserMapper.entitytoDto(savedUser);
    }

    public UserResponse findByUsername(String username){
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found: " +  username));
    return UserMapper.entitytoDto(user);
    }
}
