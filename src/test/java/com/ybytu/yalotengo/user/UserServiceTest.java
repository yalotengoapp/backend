package com.ybytu.yalotengo.user;

import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.Role;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.UserRepository;
import com.ybytu.yalotengo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ybytu.yalotengo.models.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Rafiki", "rafiki@lionking.com", "Forest#54321@", Role.USER, Integer.valueOf(3), new ArrayList<>());
    }

    @Test
    void findUserByUsername_whenUserExists_returnsUserResponse() {
        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));

        UserResponse result = userService.findByUsername("Rafiki");

        assertEquals("Rafiki", result.username());
        assertEquals("rafiki@lionking.com", result.email());

        verify(userRepository).findByUsername("Rafiki");
    }

    @Test
    void updateUser_updatesUserSuccessfully() {
        UserRequest request = new UserRequest("RafikiUpdated", "rafikiupdated@lionking.com", "#Newpass1234", Role.USER.name());

        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);


        UserResponse result = userService.updateUserByUsername("Rafiki", request);

        assertEquals("RafikiUpdated", result.username());
        assertEquals("rafikiupdated@lionking.com", result.email());
        assertEquals(Role.USER, result.role());

        verify(userRepository).save(any(User.class));
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

        assertThrows(UserNotFoundException.class, () -> userService.deleteByUsername("Nala"));
    }

}
