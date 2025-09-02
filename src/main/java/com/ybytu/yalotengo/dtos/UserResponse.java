package com.ybytu.yalotengo.dtos;
import com.ybytu.yalotengo.models.Role;
import java.util.List;

public record UserResponse(
        String username,
        String email,
        Role role,
        Integer fruit,
        List <ItemResponse> items
){
}