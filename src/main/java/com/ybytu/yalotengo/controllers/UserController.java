package com.ybytu.yalotengo.controllers;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.security.UserDetail;
import com.ybytu.yalotengo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetail userDetail) {
        UserResponse userResponse = userService.findById(id, userDetail.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/me/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetail userDetail) {
        UserResponse userResponse = userService.findByUsername(userDetail.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUserByUsername(@PathVariable String username, @Valid @RequestBody UserRequest userRequest, @AuthenticationPrincipal UserDetail userDetail) {
        UserResponse userResponse = userService.updateUserByUsername(username, userRequest, userDetail.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserResponse> deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}