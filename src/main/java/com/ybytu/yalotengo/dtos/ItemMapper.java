package com.ybytu.yalotengo.dtos;
import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.User;

public class ItemMapper{
    public static Item dtoToEntity(ItemRequest dto, User user) {
        Item item = new Item();
        item.setTitle(dto.title());
        item.setDescription(dto.description());
        item.setType(dto.type());
        item.setItemCondition(dto.itemCondition());
        item.setImgUrl(dto.imgUrl());
        item.setFruitsRequired(Integer.parseInt(dto.fruitsRequired()));
        item.setUser(user);
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
