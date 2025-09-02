package com.ybytu.yalotengo.controllers;
import com.ybytu.yalotengo.dtos.JwtResponse;
import com.ybytu.yalotengo.dtos.LoginRequest;
import com.ybytu.yalotengo.dtos.UserRequest;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.security.UserDetail;
import com.ybytu.yalotengo.security.jwt.JwtService;
import com.ybytu.yalotengo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        UserRequest userRequestWithRolByDefault = new UserRequest(userRequest.username(), userRequest.email(), userRequest.password(), "ROLE_USER");

        UserResponse userResponse = userService.addUser(userRequestWithRolByDefault);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginRequest.username(),loginRequest.password()));

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetail);
        JwtResponse jwtResponse = new JwtResponse(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }
}
