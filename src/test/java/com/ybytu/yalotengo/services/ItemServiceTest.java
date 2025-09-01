package com.ybytu.yalotengo.services;

import com.ybytu.yalotengo.dtos.*;
import com.ybytu.yalotengo.models.Item;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.repositories.ItemRepository;
import com.ybytu.yalotengo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;

    @Mock
    UserRepository userRepository;

    @Mock
    ItemRepository itemRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    private User user;

    @Test
    void shouldGetAllItemsSucessfuly() {
        User user = new User();
        user.setId(10L);
        user.setUsername("Rafiki");

        Item item1 = new Item();
        item1.setId(1L);
        item1.setTitle("Item 1");
        item1.setDescription("Best item ever.");
        item1.setType("Item");
        item1.setItemCondition("new");
        item1.setImgUrl("www.google.com");
        item1.setFruitsRequired(3);
        item1.setUser(user);

        Item item2 = new Item();
        item2.setId(2L);
        item2.setTitle("Item 2");
        item2.setDescription("Best item ever.");
        item2.setType("Item");
        item2.setItemCondition("new");
        item2.setImgUrl("www.google.com");
        item2.setFruitsRequired(3);
        item2.setUser(user);

        given(itemRepository.findAll()).willReturn(List.of(item1, item2));
        List<ItemResponse> result = itemService.getAllItems();

        assertThat(result).hasSize(2);
        assertThat((result.get(0).title())).isEqualTo("Item 1");
        assertThat((result.get(1).title())).isEqualTo("Item 2");

        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void shouldAddItemSuccessfully() {
        User user = new User();
        user.setId(10L);
        user.setUsername("Rafiki");

        ItemRequest request = new ItemRequest(
                "Item 1",
                "Best item ever.",
                "Item",
                "new",
                "www.google.com",
                "3",
                "Rafiki"
        );

        Item newItem = ItemMapper.dtoToEntity(request, user);
        when(userRepository.findByUsername("Rafiki")).thenReturn(Optional.of(user));
        when(itemRepository.save(any(Item.class))).thenReturn(newItem);
        ItemResponse savedItem = itemService.addItem(request, "Rafiki");
        assertNotNull(savedItem);
        assertEquals("Item 1", savedItem.title());
        assertEquals("Best item ever.", savedItem.description());
        assertEquals("Item", savedItem.type());
        assertEquals("new", savedItem.itemCondition());
        assertEquals("www.google.com", savedItem.imgUrl());
        assertEquals(Integer.valueOf(3), savedItem.fruitsRequired());
        assertEquals("Rafiki", savedItem.username());

        verify(userRepository, times(1)).findByUsername("Rafiki");
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void shouldUpdateItemSuccessfully() {
        User user = new User();
        user.setId(10L);
        user.setUsername("Rafiki");

        Item existingItem = new Item();
        existingItem.setId(1L);
        existingItem.setTitle("Item 1");
        existingItem.setDescription("Best item ever.");
        existingItem.setType("Item");
        existingItem.setItemCondition("new");
        existingItem.setImgUrl("www.google.com");
        existingItem.setFruitsRequired(3);
        existingItem.setUser(user);

        when(itemRepository.findByUserUsername("Rafiki"))
                .thenReturn(List.of(existingItem));

        ItemRequest request = new ItemRequest(
                "Item 1",
                "Best item ever.",
                "Item",
                "new",
                "www.google.com",
                "3",
                "Rafiki"
        );

        Item updatedItem = ItemMapper.dtoToEntity(request, user);
        updatedItem.setId(1L);

        when(itemRepository.save(any(Item.class)))
                .thenReturn(updatedItem);

        ItemResponse savedItem = itemService.updateItem(1L, request, "Rafiki");
        assertNotNull(savedItem);
        assertEquals("Item 1", savedItem.title());
        assertEquals("Best item ever.", savedItem.description());
        assertEquals("Item", savedItem.type());
        assertEquals("new", savedItem.itemCondition());
        assertEquals("www.google.com", savedItem.imgUrl());
        assertEquals(Integer.valueOf(3), savedItem.fruitsRequired());
        assertEquals("Rafiki", savedItem.username());

        verify(itemRepository, times(1)).findByUserUsername("Rafiki");
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void shouldDeleteItem() {
        User user = new User();
        user.setId(10L);
        user.setUsername("Rafiki");

        Item existingItem = new Item();
        existingItem.setId(1L);
        existingItem.setUser(user);

        when(itemRepository.findByUserUsername("Rafiki")).thenReturn(List.of(existingItem));

        itemService.deleteItem(1L, "Rafiki");

        verify(itemRepository, times(1)).findByUserUsername("Rafiki");
        verify(itemRepository).delete(argThat(item -> item.getId().equals(1L)));
    }
}