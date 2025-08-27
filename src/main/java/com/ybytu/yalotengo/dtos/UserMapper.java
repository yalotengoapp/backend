package com.ybytu.yalotengo.dtos;

import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.Role;
import com.ybytu.yalotengo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User dtoToEntity(UserRequest dto) {
        return new User(
                null,
                dto.username(),
                dto.email(),
                dto.password(),
                Role.valueOf(dto.role().replace("ROLE_", "")),
                3,
                new ArrayList<>()
        );
    }

    public static UserResponse entitytoDto(User user){
        List<ItemResponse> itemList = user.getItems().stream().map(item -> ItemMapper.entityToDto(item)).toList();
        return new UserResponse(user.getUsername(),user.getEmail(),user.getRole(),user.getFruit(),itemList);
    }
}