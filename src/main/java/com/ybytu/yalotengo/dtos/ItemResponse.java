package com.ybytu.yalotengo.dtos;

import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.ybytu.yalotengo.models.Item;

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
