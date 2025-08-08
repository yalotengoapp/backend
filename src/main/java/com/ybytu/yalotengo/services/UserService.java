package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.dtos.UserMapper;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import jakarta.transaction.Transactional;
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

    public UserResponse updateUserByUsername(String username, UserRequest userRequest){
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + "not found"));
        existingUser.setUsername(userRequest.username());
        existingUser.setEmail(userRequest.email());
        existingUser.setPassword(userRequest.password());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.entitytoDto(updatedUser);
    }

    @Transactional
    public void deleteByUsername(String username){
        findByUsername(username);
        userRepository.deleteByUsername(username);
    }
}
