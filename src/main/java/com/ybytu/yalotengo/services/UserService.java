package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public UserService(UserRepository userRepository, ItemRepository itemRepository){
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
}
