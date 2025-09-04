package com.ybytu.yalotengo.services;
import com.ybytu.yalotengo.dtos.UserMapper;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import com.ybytu.yalotengo.security.UserDetail;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ItemRepository itemRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse addUser(UserRequest userRequest){
        User newUser = UserMapper.dtoToEntity(userRequest);
        newUser.setPassword(passwordEncoder.encode(userRequest.password()));
        User savedUser = userRepository.save(newUser);
        return UserMapper.entitytoDto(savedUser);
    }

    public UserResponse findById(@PathVariable Long id, Long userId){
        User user = userRepository.findById(id)
                .filter(u -> u.getId().equals(userId))
                .orElseThrow(() -> new UserNotFoundException("Id not found: " +  id));
        return UserMapper.entitytoDto(user);
    }

    public UserResponse findByUsername(String username){
    User user = userRepository.findByUsername(username)
            .filter(u -> u.getUsername().equals(username))
            .orElseThrow(() -> new UserNotFoundException("User not found: " +  username));
    return UserMapper.entitytoDto(user);
    }

    public UserResponse updateUserByUsername(String username, UserRequest userRequest, Long id){
        User existingUser = userRepository.findByUsername(username)
                .filter(user -> user.getId().equals(id))
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found or you are not authorized"));
        existingUser.setUsername(userRequest.username());
        existingUser.setEmail(userRequest.email());
        existingUser.setPassword(passwordEncoder.encode(userRequest.password()));
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.entitytoDto(updatedUser);
    }

    @Transactional
    public void deleteByUsername(String username){
        findByUsername(username);
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserDetail(user))
                .orElseThrow(() -> new UserNotFoundException(username));
    }

}
