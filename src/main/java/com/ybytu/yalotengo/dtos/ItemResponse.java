package com.ybytu.yalotengo.dtos;

public record ItemResponse(
        String title,
        String description,
        String type,
        String itemCondition,
        String imgUrl,
        Integer fruitsRequired,
        String username
){
}
