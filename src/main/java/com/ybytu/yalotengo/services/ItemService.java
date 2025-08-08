package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.repositories.ItemRepository;

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
}