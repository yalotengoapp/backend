package com.ybytu.yalotengo.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybytu.yalotengo.dtos.ItemRequest;
import com.ybytu.yalotengo.dtos.ItemResponse;
import com.ybytu.yalotengo.models.User;
import com.ybytu.yalotengo.security.UserDetail;
import com.ybytu.yalotengo.services.ItemService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ItemResponse> itemResponse;

    @BeforeEach
    void setUp() {
        itemResponse = new ArrayList<>();

        ItemResponse item1 = new ItemResponse("Item 1", "Best item", "Item", "new", "www.google.com", 3, "Rafiki");
        ItemResponse item2 = new ItemResponse("Item 2", "Worst item", "Item", "old", "www.google.com", 1, "Rafiki");

        itemResponse.add(item1);
        itemResponse.add(item2);
    }
        @Test
        void ShouldGetAllItemsSucessfuly() throws Exception {
            given(itemService.getAllItems()).willReturn(itemResponse);

            mockMvc.perform(get("/items")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$[0].type").value("Item"))
                    .andExpect(jsonPath("$[1].type").value("Item"))
                    .andExpect(jsonPath("$[0].title").value("Item 1"))
                    .andExpect(jsonPath("$[1].title").value("Item 2"));
        }

    @WithMockUser(username = "Simba", roles = "USER")
    @Test
    @DisplayName("Save item and return item DTO")
    void givenValidItemRequest_whenSaveItem_thenReturnSavedItem() throws Exception {
        ItemRequest itemRequest = new ItemRequest("Item 01","Best item ever","Item Type","new","http://www.google.com/test.jpg","3","Simba");

        String request = objectMapper.writeValueAsString((itemRequest));

        ItemResponse mockResponse = new ItemResponse(itemRequest.title(), itemRequest.description(), itemRequest.type(), itemRequest.itemCondition(), itemRequest.imgUrl(), Integer.valueOf(itemRequest.fruitsRequired()), "Simba");

        Mockito.when(itemService.addItem(Mockito.any(ItemRequest.class),Mockito.anyString()))
                .thenReturn(mockResponse);

        String response = mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ItemResponse savedItem = objectMapper.readValue(response, ItemResponse.class);

        assertEquals("Item 01", savedItem.title());
        assertEquals("Best item ever", savedItem.description());
        assertEquals("Item Type", savedItem.type());
        assertEquals("new", savedItem.itemCondition());
        assertEquals("http://www.google.com/test.jpg", savedItem.imgUrl());
        assertEquals(3, savedItem.fruitsRequired());
        assertEquals("Simba", savedItem.username());

    }
}