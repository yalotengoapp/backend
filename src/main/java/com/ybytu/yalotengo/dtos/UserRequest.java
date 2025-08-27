package com.ybytu.yalotengo.dtos;

import com.ybytu.yalotengo.models.Role;
import jakarta.validation.constraints.*;

import javax.management.relation.RoleResult;

public record UserRequest (
    @NotBlank(message = "Username required")
    @Size(min = 3, max = 50, message = "Username must contain between 3 and 50 characters")
    String username,

    @NotBlank(message = "Email required")
    @Email(message = "Invalid e-mail format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    String email,

    @NotBlank(message = "Password required")
    @Pattern(message = "Password must contain a minimum of 12 characters, including a number, one uppercase letter, one lowercase letter and one special character, for example: Password#123",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=.])(?=\\S+$).{12,}$")
    String password,

    String role
){
}
