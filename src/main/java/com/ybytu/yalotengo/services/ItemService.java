package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.dtos.ItemMapper;
import com.ybytu.yalotengo.dtos.ItemRequest;
import com.ybytu.yalotengo.dtos.ItemResponse;
import com.ybytu.yalotengo.dtos.UserResponse;
import com.ybytu.yalotengo.exceptions.ItemNotFoundException;
import com.ybytu.yalotengo.exceptions.UserNotFoundException;
import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, UserRepository userRepository){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(item -> ItemMapper.entityToDto(item)).toList();
    }

//   public ItemResponse getItemById(Long id, String username) {
//   Item item = itemRepository.findById(id)
//   .orElseThrow(() -> new ItemNotFoundException("Item with id" + id + "not found"));
//    return ItemMapper.entityToDto(item);
//    }

    public ItemResponse addItem(ItemRequest itemRequest, String username) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User " + username + "not found"));

        Item newItem = ItemMapper.dtoToEntity(itemRequest, foundUser);
        Item savedItem = itemRepository.save(newItem);

        return ItemMapper.entityToDto(savedItem);
    }

    public ItemResponse updateItem(Long id, ItemRequest itemRequest, String username) {

        List<Item> itemsByUsername = itemRepository.findByUserUsername(username);

        Item existingItem = itemsByUsername.stream().filter(item -> item.getId().equals(id)).findFirst().orElseThrow(() -> new ItemNotFoundException("Item with id :" + id + "does not belong to user " + username));

        existingItem.setTitle(itemRequest.title());
        existingItem.setDescription(itemRequest.description());
        existingItem.setItemCondition(itemRequest.itemCondition());
        existingItem.setType(itemRequest.type());
        existingItem.setImgUrl(itemRequest.imgUrl());

        Item updatedItem = itemRepository.save(existingItem);
        return ItemMapper.entityToDto(updatedItem);
    }

    public void deleteItem(Long id, String username) {
        List<Item> itemByUsername = itemRepository.findByUserUsername(username);

        Item existingItem = itemByUsername.stream().filter(item -> item.getId().equals(id)).findFirst().orElseThrow(() -> new ItemNotFoundException("Item with id " + id + "does not belong to user " + username));
        itemRepository.delete(existingItem);
    }
}