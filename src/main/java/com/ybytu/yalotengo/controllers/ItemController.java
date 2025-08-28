package com.ybytu.yalotengo.controllers;

import com.ybytu.yalotengo.dtos.ItemRequest;
import com.ybytu.yalotengo.dtos.ItemResponse;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.security.UserDetail;
import com.ybytu.yalotengo.services.ItemService;
import com.ybytu.yalotengo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")

public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        List<ItemResponse> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
}

//  @GetMapping("/items/{id}")
//   public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id) {
//   ItemResponse itemResponse = itemService.getItemById(id);
//   return new ResponseEntity<>(itemResponse, HttpStatus.OK);
//}

    @PostMapping
    public ResponseEntity<ItemResponse> addItem(@Valid @RequestBody ItemRequest itemRequest, @AuthenticationPrincipal UserDetail userDetail) {
        ItemResponse itemResponse = itemService.addItem(itemRequest, userDetail.getUsername());
        return new ResponseEntity<>( itemResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long id, @Valid @RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.updateItem(id, itemRequest, itemRequest.username());
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id, User user){
        itemService.deleteItem(id, user.getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



