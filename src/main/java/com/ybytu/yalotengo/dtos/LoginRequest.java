package com.ybytu.yalotengo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Username required")
        @Size(min = 3, max = 50, message = "Username must contain between 3 and 50 characters")
        String username,

        @NotBlank(message = "Password required")
        String password
){
}
