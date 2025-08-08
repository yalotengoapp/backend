package com.ybytu.yalotengo.dtos;

import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.User;

public class ItemMapper{
public static Item dtoToEntity(ItemRequest dto, User user) {
    Item item = new Item();
    item.setTitle(dto.title());
    item.setTitle(dto.description());
    item.setTitle(dto.type());
    item.setTitle(dto.itemCondition());
    item.setTitle(dto.imgUrl());
    item.setTitle(dto.fruitsRequired());

    return item;
}

    public static ItemResponse entityToDto(Item item) {
        return new ItemResponse(
                item.getTitle(),
                item.getDescription(),
                item.getType(),
                item.getItemCondition(),
                item.getImgUrl(),
                item.getFruitsRequired(),
                item.getUser().getUsername()
        );
    }
}
