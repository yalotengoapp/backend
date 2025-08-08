package com.ybytu.yalotengo.controllers;

import com.ybytu.yalotengo.services.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
}
