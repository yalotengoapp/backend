package com.ybytu.yalotengo.controllers;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest) {
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.findByUsername(username);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUserByUsername(@PathVariable String username, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUserByUsername(username, userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserResponse> deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}