package com.ybytu.yalotengo.services;
import com.ybytu.yalotengo.dtos.UserMapper;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.Role;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.UserRepository;
import com.ybytu.yalotengo.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private User user;


    @BeforeEach
    void setUp() {
        user = new User(1L,
                "Rafiki",
                "rafiki@lionking.com",
                "Forest#54321@",
                Role.USER,
                Integer.valueOf(3),
                new ArrayList<>());
    }

    @Test
    void findUserByUsername_whenUserExists_returnsUserResponse() {
        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));
        UserResponse result = userService.findByUsername("Rafiki");
        assertEquals("Rafiki", result.username());
        assertEquals("rafiki@lionking.com", result.email());
        verify(userRepository, times(1)).findByUsername("Rafiki");
    }

    @Test
    public void addUser_whenValidUser_savesAndReturnsUserResponse() {
        UserRequest request = new UserRequest(
                "Rafiki",
                "rafiki@lionking.com",
                "Forest#54321@",
                "USER");

        User newUser = UserMapper.dtoToEntity(request);
        when(passwordEncoder.encode(request.password())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        UserResponse savedUser = userService.addUser(request);
        assertNotNull(savedUser);
        assertEquals("Rafiki", savedUser.username());
        assertEquals("rafiki@lionking.com", savedUser.email());
        assertEquals(Role.USER, savedUser.role());
        verify(passwordEncoder, times(1)).encode(request.password());
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void updateUser_updatesUserSuccessfully() {
        UserRequest request = new UserRequest(
                "RafikiUpdated",
                "rafikiupdated@lionking.com",
                "#Newpass1234",
                "USER");

        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponse result = userService.updateUserByUsername("Rafiki", request, user.getId());
        assertNotNull(result);
        assertEquals("RafikiUpdated", result.username());
        assertEquals("rafikiupdated@lionking.com", result.email());
        assertEquals(Role.USER, result.role());
        assertEquals("encoded_password", user.getPassword());
        verify(userRepository, times(1)).findByUsername("Rafiki");
        verify(passwordEncoder, times(1)).encode(request.password());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_whenUserExists_deleteSuccessfully() {
        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));
        userService.deleteByUsername("Rafiki");
        verify(userRepository, times(1)).deleteByUsername("Rafiki");
    }

    @Test
    void deleteUser_whenUserNotFound_throwsException() {
        when(userRepository.findByUsername("Nala")).thenReturn(Optional.empty());
        verify(userRepository, never()).deleteByUsername((anyString()));
        assertThrows(UserNotFoundException.class, () -> userService.deleteByUsername("Nala"));
    }

}
