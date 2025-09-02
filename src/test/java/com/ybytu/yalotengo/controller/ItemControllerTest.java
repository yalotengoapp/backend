package com.ybytu.yalotengo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybytu.yalotengo.dtos.ItemRequest;
import com.ybytu.yalotengo.dtos.ItemResponse;
import com.ybytu.yalotengo.services.ItemService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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
}