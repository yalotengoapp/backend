package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
}